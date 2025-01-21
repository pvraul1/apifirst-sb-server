package com.rperezv365.apifirst.apifirstserver.integration;

import com.rperezv365.apifirst.ApiClient;
import com.rperezv365.apifirst.client.CustomerApi;
import com.rperezv365.apifirst.model.CustomerDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * CustomerIT
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 21/01/2025 - 18:39
 * @since 1.17
 */
public class CustomerIT {
    CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        ApiClient apiClient = new ApiClient(new RestTemplate());
        apiClient.setBasePath("http://localhost:8080");
        customerApi = new CustomerApi(apiClient);
    }

    @Test
    void testListCustomers() {
        List<CustomerDto> customers = customerApi.listCustomers();

        assertThat(customers.size()).isGreaterThan(0);
    }

}
