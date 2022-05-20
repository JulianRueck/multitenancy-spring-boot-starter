package io.github.julianrueck.routing;

import io.github.julianrueck.core.ThreadLocalStorage;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class TenantAwareRoutingSource extends AbstractRoutingDataSource {
    @Override
    public Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantId();
    }
}
