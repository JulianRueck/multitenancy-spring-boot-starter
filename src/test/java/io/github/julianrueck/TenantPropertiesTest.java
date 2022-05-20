package io.github.julianrueck;

import com.zaxxer.hikari.HikariDataSource;
import io.github.julianrueck.autoconfigure.TenantProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TenantProperties.class)
class TenantPropertiesTest {

    @Autowired
    private TenantProperties tenantProperties;

    @Test
    void givenDataSourceMap_whenBindingPropertiesFile_thenDataSourceIsCreated() {
        // Arrange
        String driverClassName = "org.postgresql.Driver";
        String url = "jdbc:postgresql:db1";
        String username = "postgres";
        String password = "password";
        TenantProperties.DataSource dataSource = new TenantProperties.DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        TenantProperties.Tenant tenant = new TenantProperties.Tenant();
        String tenantId = "tenantId1";
        tenant.setId(tenantId);
        tenant.setDataSource(dataSource);
        List<TenantProperties.Tenant> tenantList = new ArrayList<>();
        tenantList.add(tenant);
        tenantProperties.setTenantList(tenantList);

        HikariDataSource expectedDataSource = new HikariDataSource();
        expectedDataSource.setDriverClassName(driverClassName);
        expectedDataSource.setJdbcUrl(url);
        expectedDataSource.setUsername(username);
        expectedDataSource.setPassword(password);
        // Act
        HikariDataSource actualDataSource = (HikariDataSource) tenantProperties.getDataSources().get(tenantId);
        // Assert
        assertEquals(expectedDataSource.getDriverClassName(), actualDataSource.getDriverClassName());
        assertEquals(expectedDataSource.getJdbcUrl(), actualDataSource.getJdbcUrl());
        assertEquals(expectedDataSource.getUsername(), actualDataSource.getUsername());
        assertEquals(expectedDataSource.getPassword(), actualDataSource.getPassword());
    }
}
