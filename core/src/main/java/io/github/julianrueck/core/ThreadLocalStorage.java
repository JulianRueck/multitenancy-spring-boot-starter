package io.github.julianrueck.core;

public class ThreadLocalStorage {

    private static ThreadLocal<String> tenant = new ThreadLocal<>();

    /**
     * Erases the tenant identifier from the thread.
     * Threads can be reused by other processes when the thread is 'given back' to the thread pool.
     * In order to prevent the leaking of this tenant identifier the data is to be erased using this method.
     */
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
