package com.example.multitenancy;

import com.example.multitenancy.configuration.TenantConfig;
import com.example.multitenancy.routing.TenantAwareRoutingSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement
public class PocApplication {

    @Autowired
    TenantConfig tenantConfig;

    public static void main(String[] args) {
        SpringApplication.run(PocApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
        dataSource.setTargetDataSources(tenantConfig.getDataSourceMap());
        return dataSource;
    }

}
