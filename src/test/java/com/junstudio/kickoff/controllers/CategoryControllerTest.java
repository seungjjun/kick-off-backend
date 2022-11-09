package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CategoriesDto;
import com.junstudio.kickoff.dtos.CategoryDto;
import com.junstudio.kickoff.services.GetCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCategoryService getCategoryService;

    @Test
    void category() throws Exception {
        given(getCategoryService.categories())
            .willReturn(new CategoriesDto(
                List.of(new CategoryDto(1L, "EPL", null))));

        mockMvc.perform(MockMvcRequestBuilders.get("/category"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("name\":\"EPL")
            ));
    }
}