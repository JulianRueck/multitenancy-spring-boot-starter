package io.github.julianrueck.autoconfigure;

import io.github.julianrueck.core.ThreadLocalStorage;
import io.github.julianrueck.databasepertenant.routing.TenantAwareRoutingSource;
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

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "multitenancy", name = "interceptor")
    public InterceptorConfiguration getInterceptorConfiguration() {
        return new InterceptorConfiguration();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "multitenancy", name = "implementation", havingValue = "DATABASE_PER_TENANT")
    public DataSource dataSource(TenantProperties tenantProperties) {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
        dataSource.setTargetDataSources(tenantProperties.getDataSources());
        return dataSource;
    }

}
