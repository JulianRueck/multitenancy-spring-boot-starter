package com.julianrueck.core.core;

public class ThreadLocalStorage {

    private static ThreadLocal<String> tenant = new ThreadLocal<>();

    public static void removeTenantId() {
        tenant.remove();
    }

    public static void setTenantId(String tenantId) {
        tenant.set(tenantId);
    }

    public static String getTenantId() {
        return tenant.get();
    }

}
