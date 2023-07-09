package com.cavitestate.ecommerce.controller;

import com.cavitestate.ecommerce.dto.WishlistProductDto;
import com.cavitestate.ecommerce.model.Product;
import com.cavitestate.ecommerce.model.Wishlist;
import com.cavitestate.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @GetMapping("/{email}")
    public ResponseEntity<?> getWishlistByEmail(@PathVariable String email) {
        List<Wishlist> wishlistList = wishlistService.getWishListByEmail(email);
        return ResponseEntity.ok(wishlistList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addWishlist(@RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.addWishList(wishlist);
        if (createdWishlist != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWishlist);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error creating wishlist");
        }
    }

    @GetMapping("/product/{email}")
    public ResponseEntity<?> getWishlistProductByEmail(@PathVariable String email) {
        List<WishlistProductDto> wishlistList = wishlistService.getWishlistProductByEmail(email);
        return ResponseEntity.ok(wishlistList);
    }

    @DeleteMapping("/delete/{id}/{productId}")
    public ResponseEntity<String> deleteWishlist(@PathVariable String id, @PathVariable String productId) {
        wishlistService.deleteWishlist(id, productId);
        return ResponseEntity.ok("deleted");
    }
}
