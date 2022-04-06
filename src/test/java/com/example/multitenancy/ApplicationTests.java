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

        Map<String, String> dataSourceMap = new HashMap<>();
        dataSourceMap.put("tenantId1", "driver;org.postgresql.Driver, url;jdbc:postgresql:db1, username;postgres, password;password");
        tenantConfig.setDataSourceMap(dataSourceMap);
        HikariDataSource actualDataSource = (HikariDataSource)tenantConfig.getDataSourceMap().get("tenantId1");

        HikariDataSource expectedDataSource = new HikariDataSource();
        expectedDataSource.setDriverClassName("org.postgresql.Driver");
        expectedDataSource.setJdbcUrl("jdbc:postgresql:db1");
        expectedDataSource.setUsername("postgres");
        expectedDataSource.setPassword("password");

        assertEquals(expectedDataSource.getDriverClassName(), actualDataSource.getDriverClassName());
        assertEquals(expectedDataSource.getJdbcUrl(), actualDataSource.getJdbcUrl());
        assertEquals(expectedDataSource.getUsername(), actualDataSource.getUsername());
        assertEquals(expectedDataSource.getPassword(), actualDataSource.getPassword());
    }
}
