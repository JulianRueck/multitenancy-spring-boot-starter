package io.github.julianrueck.databasepertenant.routing;

import io.github.julianrueck.core.ThreadLocalStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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