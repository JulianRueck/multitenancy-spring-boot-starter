package com.example.multitenancy.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TenantConfigTest {

    @Test
    void givenDataSourceMap_whenBindingPropertiesFile_thenDataSourceIsCreated() {
        // Arrange
        TenantConfig tenantConfig = new TenantConfig();
        TenantConfig.Tenant tenant = new TenantConfig.Tenant();
        TenantConfig.DataSource dataSource = new TenantConfig.DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql:db1");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        tenant.setId("tenantId1");
        tenant.setDataSource(dataSource);
        List<TenantConfig.Tenant> tenantList = new ArrayList();
        tenantList.add(tenant);
        tenantConfig.setTenantList(tenantList);

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