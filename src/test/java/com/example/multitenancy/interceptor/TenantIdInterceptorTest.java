package com.example.multitenancy.interceptor;

import com.example.multitenancy.core.ThreadLocalStorage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class TenantIdInterceptorTest {

    @Test
    void givenTenantIdInLocalStorage_whenRequestIsCompleted_thenTenantIdIsRemovedFromLocalStorage() throws Exception {
        // Arrange
        TenantIdInterceptor tenantIdInterceptor = new TenantIdInterceptor();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ThreadLocalStorage.setTenantId("id1");
        // Act
        tenantIdInterceptor.afterCompletion(request, response, new Object(), new Exception());
        // Assert
        assertNull(ThreadLocalStorage.getTenantId());
    }
}