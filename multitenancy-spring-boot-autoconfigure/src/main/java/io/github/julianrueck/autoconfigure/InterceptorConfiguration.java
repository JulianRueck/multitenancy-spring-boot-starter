package io.github.julianrueck.autoconfigure;

import io.github.julianrueck.interceptors.interceptor.TenantIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private MultitenancyProperties multitenancyProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(returnInterceptorImplementation());
    }

    private HandlerInterceptor returnInterceptorImplementation() {
        switch (multitenancyProperties.getInterceptor()) {
            case HEADER:
                return new TenantIdInterceptor();
            case JWT:
                // TODO: return JWT implementation
        }
        return null;
    }
}
