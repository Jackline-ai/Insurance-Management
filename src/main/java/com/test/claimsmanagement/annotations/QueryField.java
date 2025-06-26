package com.test.claimsmanagement.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryField {
    String qField() default "";

    Comparison comparison() default Comparison.EQUALS;

    enum Comparison {
        EQUALS, CONTAINS, GREATER_THAN, LESS_THAN
    }

}
