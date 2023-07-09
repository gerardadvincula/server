package com.cavitestate.ecommerce.repository;

import com.cavitestate.ecommerce.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    List<Wishlist> findByEmail(String email);

    void deleteByIdAndProductId(String id, String productId);
}
