package com.test.claimsmanagement.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditTrailImpl implements AuditorAware {

    @Override
    public Optional<String> getCurrentAuditor() {
        var context = SecurityContextHolder.getContext();
        if (context == null || context.getAuthentication() == null) {

            return Optional.of("system");
        }

        var auth = context.getAuthentication();

        if (auth.getPrincipal() instanceof org.springframework.security.oauth2.core.user.OAuth2User oauthUser) {
            // Extract email or another identifier
            String email = oauthUser.getAttribute("email");
            return Optional.ofNullable(email);
        }

        if (auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return Optional.of(auth.getName());
        }

        return Optional.of("system");
    }
}
