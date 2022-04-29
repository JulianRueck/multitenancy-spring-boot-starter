package com.julianrueck.core.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TenantConfigTest {

    @Autowired
    private TenantConfig tenantConfig;

    @Test
    void givenDataSourceMap_whenBindingPropertiesFile_thenDataSourceIsCreated() {
        // Arrange
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
