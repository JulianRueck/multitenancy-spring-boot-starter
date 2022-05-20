package io.github.julianrueck;

import io.github.julianrueck.core.ThreadLocalStorage;
import io.github.julianrueck.routing.TenantAwareRoutingSource;
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