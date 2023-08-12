package com.cavitestate.ecommerce.service;

import com.cavitestate.ecommerce.dto.ProductDto;
import com.cavitestate.ecommerce.model.Category;
import com.cavitestate.ecommerce.model.Product;
import com.cavitestate.ecommerce.repository.CategoryRepository;
import com.cavitestate.ecommerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setCategoryId(category.getId());
        product.setCreatedDate(productDto.getCreatedDate());
        productRepository.save(product);
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setCategoryId(product.getCategoryId());
        productDto.setCreatedDate(product.getCreatedDate());
        productDto.setSold(product.getSold());
        productDto.setArchived(product.getArchived());
        return productDto;
    }

    public List<ProductDto> getListProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDTOS = new ArrayList<>();
        for (Product product : allProducts) {
            productDTOS.add(getProductDto(product));
        }
        return productDTOS;
    }

    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public void updateProduct(ProductDto productDTO, String productId) {
        Optional<Product> optionalProducts = productRepository.findById(productId);
        if (!optionalProducts.isPresent()) {
            try {
                throw new Exception("Product not present!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Product products = optionalProducts.get();
        products.setName(productDTO.getName());
        products.setImageUrl(productDTO.getImageUrl());
        products.setDescription(productDTO.getDescription());
        products.setArchived(productDTO.getArchived());
        products.setPrice(productDTO.getPrice());
        products.setQuantity(productDTO.getQuantity());
        productRepository.save(products);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> updateBestSeller() {
        List<Product> allProducts = productRepository.findAll();

        // Create a map to store the best-selling product for each category
        Map<String, Product> bestSellersByCategory = new HashMap<>();

        // Iterate over all products to find the best-selling product for each category
        for (Product product : allProducts) {
            if (product.getSold() > 0) {
                String categoryId = product.getCategoryId();
                Product bestSeller = bestSellersByCategory.get(categoryId);
                if (bestSeller == null || product.getSold() > bestSeller.getSold()) {
                    bestSellersByCategory.put(categoryId, product);
                }
            }
        }

        // Reset bestSeller field for all products
        for (Product product : allProducts) {
            product.setBestSeller(false);
        }

        // Set bestSeller to true for the best-selling product in each category
        for (Product bestSeller : bestSellersByCategory.values()) {
            bestSeller.setBestSeller(true);
            productRepository.save(bestSeller);
        }

        return allProducts;
    }

    public List<Product> getBestSellerTrue() {
        return productRepository.findByBestSeller(true);
    }

    public List<Product> getAllProductArchiveFalse() {
        Boolean archive = false;
        return productRepository.findByArchived(archive);
    }

    public Product updateProductToArchiveTrue(String id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElse(null);
        assert product != null;
        product.setArchived(productDto.getArchived());
        return productRepository.save(product);
    }
}
