package com.example.multitenancy.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class ThreadLocalStorageTest {

    @Test
    void givenStoredTenantId_whenGettingTenantId_thenTenantIdIsReturned() {
        // Arrange
        String expectedTenantId = "id1";
        ThreadLocalStorage threadLocalStorage = new ThreadLocalStorage();
        ThreadLocalStorage.setTenantId(expectedTenantId);
        // Act
        String actualTenantId = ThreadLocalStorage.getTenantId();
        // Assert
        assertEquals(expectedTenantId, actualTenantId);
    }

    @Test
    void givenTenantId_whenRemovingTenantIdFromStorage_thenTenantIdIsRemovedFromStorage() {
        // Arrange
        ThreadLocalStorage threadLocalStorage = new ThreadLocalStorage();
        ThreadLocalStorage.setTenantId("id1");
        // Act
        ThreadLocalStorage.removeTenantId();
        // Assert
        assertNull(ThreadLocalStorage.getTenantId());
    }
}