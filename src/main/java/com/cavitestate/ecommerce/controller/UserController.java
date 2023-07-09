package com.cavitestate.ecommerce.controller;

import com.cavitestate.ecommerce.dto.LoginDto;
import com.cavitestate.ecommerce.model.User;
import com.cavitestate.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginDto loginDto) throws Exception {
        return userService.loginUser(loginDto.getEmail(), loginDto.getPassword());
    }

    @GetMapping("/list")
    public List<User> getListUserController() {
        return userService.getListUser();
    }

    @GetMapping("/{email}")
    private User getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

//    @PutMapping("/{email}/address")
//    public ResponseEntity<?> updateUserAddress(@PathVariable String email, @RequestBody String newAddress) {
//        try {
//            userService.updateUserAddress(email, newAddress);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PutMapping("/changePassword/{email}")
    public ResponseEntity<String> updatePassword(@PathVariable("email") String email, @RequestBody User user) {
        userService.updatePassword(email, user);
        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
    }

    @PutMapping("/changeAddress/{email}")
    public ResponseEntity<String> updateAddress(@PathVariable("email") String email, @RequestBody User user) {
        userService.updateAddress(email, user);
        return new ResponseEntity<>("address updated successfully", HttpStatus.OK);
    }

    @PutMapping("/image/{email}")
    public ResponseEntity<String> updateUserImageUrl(@PathVariable("email") String email, @RequestBody User user) {
        userService.updateUserImageUrl(email, user);
        return new ResponseEntity<>("ImageUrl updated successfully", HttpStatus.OK);
    }

}
