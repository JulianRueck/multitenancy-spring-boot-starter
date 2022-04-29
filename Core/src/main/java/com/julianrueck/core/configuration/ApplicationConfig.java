package com.julianrueck.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import com.julianrueck.core.routing.TenantAwareRoutingSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public DataSource dataSource(TenantConfig tenantConfig) {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
        dataSource.setTargetDataSources(tenantConfig.getDataSources());
        return dataSource;
    }
}
