package io.github.julianrueck.autoconfigure;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties("multitenancy")
public class TenantProperties {

    private List<Tenant> tenantList;

    private Map<Object, Object> createDataSources() {
        if (tenantList.isEmpty()) {
            throw new IllegalStateException("List containing tenants cannot be empty. Please set at least one tenant in the application.yml file.");
        }
        Map<Object, Object> dataSources = new HashMap<>();
        for (Tenant tenant : tenantList) {
            DataSource ymlDataSource = tenant.getDataSource();
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(ymlDataSource.getDriverClassName());
            dataSource.setJdbcUrl(ymlDataSource.url);
            dataSource.setUsername(ymlDataSource.username);
            dataSource.setPassword(ymlDataSource.password);

            dataSources.put(tenant.getId(), dataSource);
        }
        return dataSources;
    }

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
