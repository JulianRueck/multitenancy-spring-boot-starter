package com.example.multitenancy.configuration;

import com.example.multitenancy.routing.TenantAwareRoutingSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Autowired
    TenantConfig tenantConfig;

    @Bean
    public DataSource dataSource() {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
        dataSource.setTargetDataSources(tenantConfig.getDataSourceMap());
        return dataSource;
    }
}
