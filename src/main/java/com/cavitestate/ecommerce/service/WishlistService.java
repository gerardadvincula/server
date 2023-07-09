package com.cavitestate.ecommerce.service;

import com.cavitestate.ecommerce.dto.WishlistProductDto;
import com.cavitestate.ecommerce.model.Product;
import com.cavitestate.ecommerce.model.Wishlist;
import com.cavitestate.ecommerce.repository.ProductRepository;
import com.cavitestate.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Wishlist> getWishListByEmail(String email) {
        return wishlistRepository.findByEmail(email);
    }

    public Wishlist addWishList(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }


    public List<WishlistProductDto> getWishlistProductByEmail(String email) {
        List<Wishlist> wishlist = wishlistRepository.findByEmail(email);
        List<WishlistProductDto> productList = new ArrayList<>();

        for (Wishlist w : wishlist) {
            Optional<Product> product = productRepository.findById(w.getProductId());
            product.ifPresent(p -> productList.add(new WishlistProductDto(w.getId(), p)));
        }

        return productList;
    }


    public void deleteWishlist(String wishlistId, String productId){
        wishlistRepository.deleteByIdAndProductId(wishlistId, productId);
    }

}
