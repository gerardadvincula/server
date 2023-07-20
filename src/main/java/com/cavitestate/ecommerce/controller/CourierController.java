package com.cavitestate.ecommerce.controller;

import com.cavitestate.ecommerce.model.Courier;
import com.cavitestate.ecommerce.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courier")
public class CourierController {

    @Autowired
    CourierService courierService;

    @PostMapping("/create")
    ResponseEntity<String> addCourier(@RequestBody Courier courier) {
        courierService.addCourier(courier);
        return ResponseEntity.ok("Courier added");
    }

    @GetMapping("/list")
    ResponseEntity<List<Courier>> getCourierList() {
        List<Courier> courierList = courierService.getCourierList();
        return ResponseEntity.ok(courierList);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Courier> updateCourier(@PathVariable String id, @RequestBody Courier courier) {
        Courier updatedCourier = courierService.updateCourier(id, courier);
        return ResponseEntity.ok(updatedCourier);
    }


    @GetMapping("/{id}")
    private ResponseEntity<Courier> getCourierById(@PathVariable String id) {
        Courier updatedCourier = courierService.getCourierById(id);
        return ResponseEntity.ok(updatedCourier);
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteCourier(@PathVariable String id) {
        courierService.deleteCourier(id);
        return ResponseEntity.ok("deleted Courier with id: " + id);
    }
}
