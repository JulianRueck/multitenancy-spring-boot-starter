package io.github.julianrueck.autoconfigure;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties("multitenancy")
public class TenantProperties {

    private List<Tenant> tenantList;

    // TODO: Add options for all other Hikari DataSource settings; timeouts, poolsize, etc.

    /**
     * Creates concrete HikariDataSource objects from user input provided in the application.yml file.
     * @return The map of datasources.
     */
    private Map<Object, Object> createDataSources() {
        if (tenantList.isEmpty()) {
            throw new IllegalStateException("List containing tenants cannot be empty. Please set at least one tenant in the application.yml file.");
        }
        Map<Object, Object> dataSources = new HashMap<>();
        for (Tenant tenant : tenantList) {
            DataSource ymlDataSource = tenant.getDataSource();
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(ymlDataSource.driverClassName);
            dataSource.setJdbcUrl(ymlDataSource.url);
            dataSource.setUsername(ymlDataSource.username);
            dataSource.setPassword(ymlDataSource.password);

            dataSources.put(tenant.getId(), dataSource);
        }
        return dataSources;
    }

    /**
     * Calls private method to generate concrete data sources from user input provided in the application.yml file.
     * @return The map of data sources.
     */
    public Map<Object, Object> getDataSources() {
        return createDataSources();
    }

    public List<Tenant> getTenantList() {
        return tenantList;
    }

    public void setTenantList(List<Tenant> tenantList) {
        this.tenantList = tenantList;
    }

    public static class Tenant {
        private String id;
        private DataSource dataSource;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public DataSource getDataSource() {
            return dataSource;
        }

        public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }
    }

    public static class DataSource {
        private String driverClassName;
        private String url;
        private String username;
        private String password;

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
