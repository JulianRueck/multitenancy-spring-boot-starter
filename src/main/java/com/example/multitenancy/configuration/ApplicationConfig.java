package com.example.multitenancy.configuration;

import com.example.multitenancy.routing.TenantAwareRoutingSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public DataSource dataSource(TenantConfig tenantConfig) {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
        dataSource.setTargetDataSources(tenantConfig.getDataSources());
        return dataSource;
    }
}
