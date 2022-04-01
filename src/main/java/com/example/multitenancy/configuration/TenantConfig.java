package com.example.multitenancy.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties("tenant")
public class TenantConfig {

    private Map<String, String> dataSourceMap;

    private Map<Object, Object> createDataSources() {
        Map<Object, Object> dataSources = new HashMap<>();
        for (String key : dataSourceMap.keySet()) {
            Map<String, String> holder = new HashMap();
            String[] keyVals = dataSourceMap.get(key).split(", ");
            for(String keyVal:keyVals)
            {
                String[] parts = keyVal.split(";");
                holder.put(parts[0],parts[1]);
            }
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(holder.get("driver"));
            dataSource.setJdbcUrl(holder.get("url"));
            dataSource.setUsername(holder.get("username"));
            dataSource.setPassword(holder.get("password"));

            dataSources.put(key, dataSource);
        }
        return dataSources;
    }


    public Map<Object, Object> getDataSourceMap() {
        return createDataSources();
    }

    public void setDataSourceMap(Map<String, String> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }
}
