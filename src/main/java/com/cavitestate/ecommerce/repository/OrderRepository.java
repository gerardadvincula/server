package com.cavitestate.ecommerce.repository;
import com.cavitestate.ecommerce.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByStatus(String status);

    List<Order> findByEmail(String email);
}
