package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.CategoryRequest;
import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import com.stock.sweet.sweetstockapi.mapper.CategoryMapper;
import com.stock.sweet.sweetstockapi.model.Category;
import com.stock.sweet.sweetstockapi.repository.CategoryRepository;
import com.stock.sweet.sweetstockapi.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryController.class})
class CategoryControllerTest {
    @Autowired
    private CategoryController con;

    @MockBean
    private CategoryMapper map;

    @MockBean
    private CategoryService service;

    @MockBean
    private CategoryRepository rep;
    @MockBean
    private CategoryRequest categoryRequest;
    @MockBean
    private CategoryResponse res;

    @Test
    void createCategory() {
            Category c1 = mock(Category.class);
            Category c2 = mock(Category.class);
            List<Category> listMock= List.of(c1,c2);
        CategoryRequest req = mock(CategoryRequest.class);
        when(categoryRequest.getName()).thenReturn("test");
        when(categoryRequest.getStorageTemperature()).thenReturn(10.2);
        when(categoryRequest.getIsRefrigerated()).thenReturn(true);
        when (categoryRequest.getUnitMeasurement()).thenReturn("liters");
        CategoryResponse resposta= con.createCategory(req);
    }
    @Test
    void getAllCategories() {
        Category c1 = mock(Category.class);
        Category c2 = mock(Category.class);
        CategoryResponse res1 = mock(CategoryResponse.class);
        List<Category> listMock= List.of(c1,c2);
        when(map.convertModelListToResponseList(listMock)).thenReturn(Collections.singletonList(res));

        when(service.getAllCategories()).thenReturn(listMock);
        List<CategoryResponse> resposta =  con.getAllCategories();

    }

    @Test
    void getCategoryByUuid() {
        Category c1 = mock(Category.class);
        Category c2 = mock(Category.class);
        CategoryResponse res1 = mock(CategoryResponse.class);
        Optional<Category> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        when(map.convertModelToResponse(c1)).thenReturn(res1);
        try {
            when(con.getCategoryByUuid(uuid)).thenReturn(res1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertEquals(res1, con.getCategoryByUuid(uuid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}