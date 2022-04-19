package com.example.multitenancy.routing;

import com.example.multitenancy.core.ThreadLocalStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenantAwareRoutingSourceTest {

    @Test
    void whenTenantIdIsSetInLocalStorage_whenLookupKeyIsDetermined_thenTenantIdIsReturned() {
        //Arrange
        TenantAwareRoutingSource tenantAwareRoutingSource = new TenantAwareRoutingSource();
        String expectedTenantId = "id1";
        ThreadLocalStorage.setTenantId(expectedTenantId);
        // Act
        Object actualTenantId = tenantAwareRoutingSource.determineCurrentLookupKey();
        // Assert
        assertEquals(expectedTenantId,actualTenantId);
    }
}