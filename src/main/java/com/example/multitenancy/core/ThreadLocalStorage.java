package com.example.multitenancy.core;

public class ThreadLocalStorage {

    private static ThreadLocal<String> tenant = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        tenant.set(tenantId);
    }

    public static String getTenantId() {
        return tenant.get();
    }

}