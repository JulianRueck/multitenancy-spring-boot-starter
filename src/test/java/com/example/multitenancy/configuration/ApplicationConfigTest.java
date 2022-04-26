package com.example.multitenancy.configuration;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @RestController
    public class TestCustomerController {

        @Autowired
        TestRepository testRepository;

        @GetMapping(TEST_URI)
        public ResponseEntity<List<TestCustomer>> getAllCustomers() {
            List<TestCustomer> customers = testRepository.findAll();
            return new ResponseEntity<List<TestCustomer>>(customers, HttpStatus.OK);
        }
    }

    @Repository
    @Transactional
    public interface TestRepository extends JpaRepository<TestCustomer, Long> {
    }


    @Table
    @Entity
    public class TestCustomer {

        @Id
        private long id;
        private String name;

        public TestCustomer() {
        }

        public TestCustomer(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return this.id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}