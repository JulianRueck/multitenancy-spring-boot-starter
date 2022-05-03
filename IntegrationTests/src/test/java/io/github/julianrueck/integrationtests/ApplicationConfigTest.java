package io.github.julianrueck.integrationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Sql("**/test/resources/sql/*.sql")
@AutoConfigureMockMvc
class ApplicationConfigTest {

    private static final String TEST_URI = "/test";

    @Autowired
    private MockMvc mockMvc;

    @Container
    private static PostgreSQLContainer container1 = new PostgreSQLContainer("postgres:latest")
            .withUsername("postgres")
            .withPassword("password")
            .withDatabaseName("testDb1");

    @Container
    private static PostgreSQLContainer container2 = new PostgreSQLContainer("postgres:latest")
            .withUsername("postgres")
            .withPassword("password")
            .withDatabaseName("testDb2");

    @DynamicPropertySource
    public static void configureTestContainersProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container1::getJdbcUrl);
        registry.add("spring.datasource.username", container1::getUsername);
        registry.add("spring.datasource.password", container1::getPassword);

        registry.add("spring.datasource.url", container2::getJdbcUrl);
        registry.add("spring.datasource.username", container2::getUsername);
        registry.add("spring.datasource.password", container2::getPassword);
    }

    @Test
    public void given_when_then() throws Exception {
        // Arrange
        String expectedName = "TestCustomer1";
        // Act
        this.mockMvc.perform(MockMvcRequestBuilders.get("https://localhost:8080/test")
                        .header("tenantId", "tenantId1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().string("{\"id\":1, "name":"TestCustomer1"}"));
        // Assert

    }
}