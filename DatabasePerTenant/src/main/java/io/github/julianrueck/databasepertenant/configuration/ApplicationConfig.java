package io.github.julianrueck.databasepertenant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import io.github.julianrueck.databasepertenant.routing.TenantAwareRoutingSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public DataSource dataSource(TenantConfig tenantConfig) {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
        dataSource.setTargetDataSources(tenantConfig.getDataSources());
        return dataSource;
    }
}
