package com.example.multitenancy;

import com.example.multitenancy.configuration.TenantConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private TenantConfig tenantConfig;

    @Test
    void givenDataSourceMap_whenBindingPropertiesFile_thenDataSourceIsCreated() {
        // Arrange
        TenantConfig.Tenant tenant = new TenantConfig.Tenant();
        TenantConfig.DataSource dataSource = new TenantConfig.DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql:db1");
        dataSource.setDriverClassName("postgres");
        dataSource.setDriverClassName("org.postgresql.Driver");
        tenant.setId("tenantId1");
        tenant.setDataSource(dataSource);

        HikariDataSource expectedDataSource = new HikariDataSource();
        expectedDataSource.setDriverClassName("org.postgresql.Driver");
        expectedDataSource.setJdbcUrl("jdbc:postgresql:db1");
        expectedDataSource.setUsername("postgres");
        expectedDataSource.setPassword("password");
        // Act
        HikariDataSource actualDataSource = (HikariDataSource) tenantConfig.getDataSources().get("tenantId1");
        // Assert
        assertEquals(expectedDataSource.getDriverClassName(), actualDataSource.getDriverClassName());
        assertEquals(expectedDataSource.getJdbcUrl(), actualDataSource.getJdbcUrl());
        assertEquals(expectedDataSource.getUsername(), actualDataSource.getUsername());
        assertEquals(expectedDataSource.getPassword(), actualDataSource.getPassword());
    }
}
