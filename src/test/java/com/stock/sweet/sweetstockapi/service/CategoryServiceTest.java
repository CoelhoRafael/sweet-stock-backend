package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.CategoryRequest;
import com.stock.sweet.sweetstockapi.model.Category;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryService.class})
class CategoryServiceTest {
   @Autowired
   private CategoryService service;

    @MockBean
    private CategoryRepository rep;
    @MockBean
    private CategoryRequest categoryRequest;

    @Test
    @DisplayName("Should return category")
    void createCategory() {
        Category c1 = mock(Category.class);
        when(rep.save(c1)).thenReturn(c1);
        assertEquals(c1, service.createCategory(c1));
    }

    @Test
    @DisplayName("Should Return exception")
    void createCategoryError() {
        Category c1 = mock(Category.class);
        when(rep.save(c1)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCategory(null);
        });

    }
    @Test
    @DisplayName("Should return all categories")
    void getAllCategories() {
        Category c1 = mock(Category.class);
        Category c2 = mock(Category.class);
        List<Category> listMock= List.of(c1,c2);
        when(rep.findAll()).thenReturn(listMock);
        assertEquals(listMock, service.getAllCategories());
    }
    @Test
    @DisplayName("Should return exception")
    void getAllCategoriesEmpty() {
        Category c1 = mock(Category.class);
        when(rep.findAll()).thenReturn(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> {
            service.getAllCategories();
        });

    }

    @Test
    @DisplayName("Should return categories by uuid")
    void findCategoryByUuid() {
        Category c1 = mock(Category.class);
        Optional<Category> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        try {
            assertEquals(c1, service.findCategoryByUuid(uuid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Should Return exception")
    void findCategoryByUuidError() {
        Category c1 = mock(Category.class);
        Optional<Category> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        String uuidFalse = "dsadfasfsd";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        assertThrows(Exception.class, () -> {
            service.findCategoryByUuid(uuidFalse);
        });
    }

    @Test
    @DisplayName("Should Return update")
    void updateCategory() {
       Category c1 = mock(Category.class);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        Optional<Category> listMock= Optional.of(c1);
        CategoryRequest req = mock(CategoryRequest.class);
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        when(categoryRequest.getName()).thenReturn("test");
        when(categoryRequest.getStorageTemperature()).thenReturn(10.2);
        when(categoryRequest.getIsRefrigerated()).thenReturn(true);
        when (categoryRequest.getUnitMeasurement()).thenReturn("liters");
        try {
            assertEquals(c1, service.updateCategory(uuid,req));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Should Return category deleted")
    void deleteCategory() {
        Category c1 = mock(Category.class);
        Optional<Category> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        try {
            assertEquals(c1, service.deleteCategory(uuid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}