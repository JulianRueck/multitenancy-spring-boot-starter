package io.github.julianrueck.databasepertenant.routing;

import io.github.julianrueck.core.ThreadLocalStorage;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class TenantAwareRoutingSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantId();
    }
}
