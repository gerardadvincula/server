package com.cavitestate.ecommerce.repository;
import com.cavitestate.ecommerce.model.Courier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends MongoRepository<Courier, String> {
}
