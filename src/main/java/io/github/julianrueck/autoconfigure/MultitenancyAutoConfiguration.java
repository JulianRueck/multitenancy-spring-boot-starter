package io.github.julianrueck.autoconfigure;

import io.github.julianrueck.core.ThreadLocalStorage;
import io.github.julianrueck.routing.TenantAwareRoutingSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(ThreadLocalStorage.class)
@EnableConfigurationProperties(MultitenancyProperties.class)
public class MultitenancyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TenantProperties tenantProperties() {
        return new TenantProperties();
    }

    /**
     * Creates a new InterceptorConfiguration Bean which is an implementation of WebMvcConfigurer.
     * It registers the interceptor - the user provides in the application.yml file - to the InterceptorRegistry.
     * @return new InterceptorConfiguration.
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "multitenancy", name = "interceptor")
    public InterceptorConfiguration InterceptorConfiguration() {
        return new InterceptorConfiguration();
    }

    /**
     * Creates a new AbstractRoutingDataSource object and sets its target data sources as specified by user.
     * @param tenantProperties
     * @return new DataSource Bean containing multiple data sources.
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "multitenancy", name = "implementation", havingValue = "DATABASE_PER_TENANT")
    public DataSource dataSource(TenantProperties tenantProperties) {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
        dataSource.setTargetDataSources(tenantProperties.getDataSources());
        return dataSource;
    }

}
