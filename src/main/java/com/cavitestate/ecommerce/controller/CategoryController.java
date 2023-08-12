package com.cavitestate.ecommerce.controller;

import com.cavitestate.ecommerce.dto.CategoryDto;
import com.cavitestate.ecommerce.model.Category;
import com.cavitestate.ecommerce.model.Product;
import com.cavitestate.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public String createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return "success created category";
    }

    @GetMapping("/list")
    public List<Category> getListCategory() {
        return categoryService.getListCategory();
    }

//    @GetMapping("/list/{categoryId}")
//    private Category getCategory(@PathVariable("categoryId") int categoryId) {
//        return categoryService.getCategoryById(categoryId);
//    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<Product>> getCategoryById(@PathVariable String id) {
        try {
            List<Product> productList = categoryService.findCategoryById(id);
            return ResponseEntity.ok(productList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{categoryId}")
    public Category updateCategory(@PathVariable("categoryId") String categoryId, @RequestBody Category category) {
        categoryService.updateCategory(categoryId, category);
        return category;
    }

    @DeleteMapping("/delete/{categoryId}")
    private String deleteCategory(@PathVariable("categoryId") String categoryId) {
        categoryService.deleteCategory(categoryId);
        return "category deleted";
    }
}
