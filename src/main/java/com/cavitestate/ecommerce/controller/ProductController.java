package com.cavitestate.ecommerce.controller;

import com.cavitestate.ecommerce.dto.ProductDto;
import com.cavitestate.ecommerce.model.Category;
import com.cavitestate.ecommerce.model.Product;
import com.cavitestate.ecommerce.repository.CategoryRepository;
import com.cavitestate.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/create")
    public String createProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategories = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategories.isPresent()) {
            return "category does not exist";
        } else {
            productService.createProduct(productDto, optionalCategories.get());
            return "success created product";
        }
    }

    @GetMapping("/list")
    public List<ProductDto> getListProduct() {
        return productService.getListProducts();
    }

    @PutMapping("/update/{productId}")
    public String updateProduct(@PathVariable("productId") String productId, @RequestBody ProductDto productDto) {
        productService.updateProduct(productDto, productId);
        return "product successfully updated !";
    }

    @GetMapping("/list/{productId}")
    private Product getProduct(@PathVariable("productId") String productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/delete/{productId}")
    private String deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
        return "deleted";
    }

//    @GetMapping("/list/search")
//    private List<Product> searchProducts(@RequestParam String products) {
//        return productService.searchProduct(products);
//    }

    @GetMapping("/best-sellers")
    public List<Product> getBestSellersPerCategory() {
        return productService.updateBestSeller();
    }

    @GetMapping("/best-sellers-true")
    public List<Product> getBestSellerTrue() {
        return productService.getBestSellerTrue();
    }

    @GetMapping("/archive-false")
    public List<Product> getProductsByArchiveFalse() {
        return productService.getAllProductArchiveFalse();
    }

    @PutMapping("/update/archive/{id}")
    public Product updateArchiveTrue(@PathVariable String id) {
        return productService.updateProductToArchiveTrue(id);
    }

}
