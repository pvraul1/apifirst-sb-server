package com.rperezv365.apifirst.apifirstserver.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * CategoryControllerTest
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 20/01/2025 - 13:15
 * @since 1.17
 */
@SpringBootTest
public class CategoryControllerTest extends BaseTest {

    @Test
    void testListCategories() throws Exception {
        mockMvc.perform(get(CategoryController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(2)))
                .andExpect(openApi().isValid(super.validator));
    }

}
