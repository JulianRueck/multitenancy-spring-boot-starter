package com.example.multitenancy.datasource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;
import java.util.Map;

public class DataSourceMap {
    public static Map<Object, Object> getDataSourceHashMap() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql:db1");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");


        DriverManagerDataSource dataSource2 = new DriverManagerDataSource();
        dataSource2.setDriverClassName("org.postgresql.Driver");
        dataSource2.setUrl("jdbc:postgresql:db2");
        dataSource2.setUsername("postgres");
        dataSource2.setPassword("password");

        HashMap hashMap = new HashMap();
        hashMap.put("tenantId1", dataSource);
        hashMap.put("tenantId2", dataSource2);
        return hashMap;
    }
}
