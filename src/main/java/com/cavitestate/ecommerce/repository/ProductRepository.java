package com.cavitestate.ecommerce.repository;

import com.cavitestate.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

//    @Query("SELECT p FROM Product p WHERE p.name LIKE %:search%")
//    List<Product> searchProduct(String search);
//
//    Optional<Product> findFirstByCategoryIdOrderBySoldDesc(String categoryId);

    List<Product> findByArchived(Boolean archived);

    List<Product> findAllByCategoryIdAndArchived(String categoryId, Boolean archived);

    List<Product> findByBestSeller(Boolean bestSeller);
}
