package com.cavitestate.ecommerce.service;

import com.cavitestate.ecommerce.model.Courier;
import com.cavitestate.ecommerce.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

    @Autowired
    CourierRepository courierRepository;


    public void addCourier(Courier courier) {
        courierRepository.save(courier);
    }

    public List<Courier> getCourierList() {
        return courierRepository.findAll();
    }

    public Courier updateCourier(String id, Courier courier) {
        Courier updateCourier = courierRepository.findById(id).orElse(null);
        if (updateCourier != null) {
            updateCourier.setCourierName(courier.getCourierName());
            updateCourier.setCourierWebsite(courier.getCourierWebsite());
            courierRepository.save(updateCourier);
        }
        return updateCourier;
    }

    public void deleteCourier(String id) {
        Courier updateCourier = courierRepository.findById(id).orElse(null);
        courierRepository.deleteById(id);
    }

}