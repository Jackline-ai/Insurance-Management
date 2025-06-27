package com.test.claimsmanagement.utils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.test.claimsmanagement.annotations.QueryField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class GenericQuerydslFilterBuilder {

    private static final Logger logger = LoggerFactory.getLogger(GenericQuerydslFilterBuilder.class);

    public <T> BooleanBuilder buildPredicate(T criteria, EntityPath<?> entityPath) {
        BooleanBuilder builder = new BooleanBuilder();
        Field[] fields = criteria.getClass().getDeclaredFields();

        for (Field dtoField : fields) {
            dtoField.setAccessible(true);
            try {
                Object value = dtoField.get(criteria);
                logger.info("=====================dtoField====: {} : {}", dtoField.getName(), value);
                if (value == null) continue;

                QueryField queryFieldAnnotation = dtoField.getAnnotation(QueryField.class);
                String dtoFieldName = dtoField.getName();
                String qFieldName = (queryFieldAnnotation != null && !queryFieldAnnotation.qField().isEmpty())
                        ? queryFieldAnnotation.qField()
                        : dtoFieldName;

                QueryField.Comparison comparison = queryFieldAnnotation != null
                        ? queryFieldAnnotation.comparison()
                        : QueryField.Comparison.EQUALS;

                Field qField;
                try {
                    qField = entityPath.getClass().getDeclaredField(qFieldName);
                } catch (NoSuchFieldException nsfe) {
                    logger.warn("Field '{}' not found in Q-class '{}'. Skipping.", qFieldName, entityPath.getClass().getSimpleName());
                    continue;
                }

                qField.setAccessible(true);
                Object path = qField.get(entityPath);

                switch (comparison) {
                    case EQUALS -> {
                        if (path instanceof StringPath sp) {
                            builder.and(sp.equalsIgnoreCase(value.toString()));
                        } else if (path instanceof NumberPath<?> np && value instanceof Number) {
                            builder.and(((NumberPath) np).eq(value));
                        } else if (path instanceof EnumPath<?> ep && value instanceof Enum<?>) {
                            builder.and(((EnumPath) ep).eq(value));
                        } else if (path instanceof BooleanPath bp && value instanceof Boolean) {
                            builder.and(bp.eq((Boolean) value));
                        } else if (path instanceof DatePath<?> dp && value instanceof Comparable<?>) {
                            builder.and(((DatePath) dp).eq(value));
                        } else {
                            logger.warn("Unsupported path type for EQUALS: {}", path.getClass().getSimpleName());
                        }
                    }

                    case CONTAINS -> {
                        if (path instanceof StringPath sp) {
                            builder.and(sp.containsIgnoreCase(value.toString()));
                        } else {
                            logger.warn("CONTAINS is only applicable to StringPath. Got: {}", path.getClass().getSimpleName());
                        }
                    }
                    case GREATER_THAN_OR_EQUALS -> {
                        if (path instanceof DatePath<?> dp && value instanceof Comparable<?>) {
                            builder.and(((DatePath) dp).goe((Comparable) value));
                        }
                    }
                    case LESS_THAN_OR_EQUALS -> {
                        if (path instanceof DatePath<?> dp && value instanceof Comparable<?>) {
                            builder.and(((DatePath) dp).loe((Comparable) value));
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                logger.error("Access error on field '{}': {}", dtoField.getName(), e.getMessage());
            } catch (Exception ex) {
                logger.error("Unexpected error while processing field '{}': {}", dtoField.getName(), ex.getMessage(), ex);
            }
        }


        return builder;
    }

    public <T> List<T> fetchWithPagination(JPAQuery<T> query, Pageable pageable) {
        logger.debug("Applying pagination: offset={}, limit={}", pageable.getOffset(), pageable.getPageSize());
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        return query.fetch();
    }
}
