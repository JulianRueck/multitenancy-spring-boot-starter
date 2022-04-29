package com.julianrueck.core.routing;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.julianrueck.core.core.ThreadLocalStorage;

public class TenantAwareRoutingSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantId();
    }
}
