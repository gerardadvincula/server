package com.cavitestate.ecommerce.service;

import com.cavitestate.ecommerce.model.Category;
import com.cavitestate.ecommerce.model.Product;
import com.cavitestate.ecommerce.repository.CategoryRepository;
import com.cavitestate.ecommerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getListCategory() {
        return categoryRepository.findAll();
    }

//    public Category getCategoryById(Integer categoryId) {
//        return categoryRepository.findById(categoryId).orElse(null);
//    }

    public List<Product> findCategoryById(String categoryId){
        Boolean archived = false;
        productService.updateBestSeller();
        return productRepository.findAllByCategoryIdAndArchived(categoryId, archived);
    }

    public void updateCategory(String categoryId, Category getCategory) {
        Category setCategory = categoryRepository.findById(categoryId).orElse(null);
        assert setCategory != null;
        setCategory.setCategoryName(getCategory.getCategoryName());
        setCategory.setDescription(getCategory.getDescription());
        setCategory.setImageUrl(getCategory.getImageUrl());
        categoryRepository.save(setCategory);
    }

    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}