package io.github.julianrueck;

import io.github.julianrueck.core.ThreadLocalStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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