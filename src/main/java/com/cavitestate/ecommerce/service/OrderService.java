package com.cavitestate.ecommerce.service;

import com.cavitestate.ecommerce.dto.OrderDto;
import com.cavitestate.ecommerce.dto.ProductQuantityDto;
import com.cavitestate.ecommerce.model.Order;
import com.cavitestate.ecommerce.model.Product;
import com.cavitestate.ecommerce.model.User;
import com.cavitestate.ecommerce.repository.OrderRepository;
import com.cavitestate.ecommerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Transactional
    public void createOrder(OrderDto orderDTO, User user) {
        Order order = new Order();
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());
        order.setProofPayment(orderDTO.getProofPayment());
        order.setDateNow(orderDTO.getDateNow());
        order.setEmail(orderDTO.getEmail());
        order.setUserFullName(orderDTO.getUserFullName());
        order.setOrderJsonList(orderDTO.getOrderJsonList());
//        order.setAddress(orderDTO.getAddress());
//        order.setCity(orderDTO.getCity());
//        order.setPostalCode(orderDTO.getPostalCode());
        order.setModeOfPayment(orderDTO.getModeOfPayment());

        List<ProductQuantityDto> productQuantities = orderDTO.getProducts();
        subtractProductsFromInventory(productQuantities);
        updateProductSold(productQuantities);

        orderRepository.save(order);
    }

    private void subtractProductsFromInventory(List<ProductQuantityDto> productQuantities) {
        for (ProductQuantityDto pq : productQuantities) {
            Optional<Product> productOptional = productRepository.findById(pq.getProductId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setQuantity(product.getQuantity() - pq.getQuantity());
                productRepository.save(product);
            } else {
                log.error("error in subtractProductsFromInventory");
            }
        }
    }

    public void updateProductSold(List<ProductQuantityDto> productQuantities) {
        for (ProductQuantityDto pq : productQuantities) {
            Optional<Product> productOptional = productRepository.findById(pq.getProductId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setSold(product.getSold() + pq.getQuantity());
                productRepository.save(product);
            } else {
                log.error("error in updateProductSold");
            }
        }
    }

    public List<Order> getListOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public void updateOrderById(String orderId, Order getOrder) {
        Order setOrder = orderRepository.findById(orderId).orElse(null);
        assert setOrder != null;
        setOrder.setOrderJsonList(getOrder.getOrderJsonList());
        orderRepository.save(setOrder);
    }

    public void updateStatusById(String orderId, Order getOrder) {
        Order setOrder = orderRepository.findById(orderId).orElse(null);

        // Retain the current trackingNum if the new value is null or blank
        if (getOrder.getTrackingNum() != null && !getOrder.getTrackingNum().isEmpty()) {
            assert setOrder != null;
            setOrder.setTrackingNum(getOrder.getTrackingNum());
        }

        // Retain the current status if the new value is null or blank
        if (getOrder.getStatus() != null && !getOrder.getStatus().isEmpty()) {
            assert setOrder != null;
            setOrder.setStatus(getOrder.getStatus());
        }

        if (getOrder.getCourier() != null && !getOrder.getCourier().isEmpty()) {
            assert setOrder != null;
            setOrder.setCourier(getOrder.getCourier());
        }

        assert setOrder != null;
        orderRepository.save(setOrder);
    }

    public void updateProofPaymentById(String orderId, Order getOrder) {
        Order setOrder = orderRepository.findById(orderId).orElse(null);
        assert setOrder != null;
        setOrder.setProofPayment(getOrder.getProofPayment());
        orderRepository.save(setOrder);
    }

    public void deleteOrderById(String orderId, Order order) {
        orderRepository.deleteById(orderId);
    }

    public List<Map<String, Object>> getTotalSalesPerMonth() {
        List<Order> deliveredOrders = orderRepository.findByStatus("Completed");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM");
        return deliveredOrders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getDateNow().format(String.valueOf(formatter)),
                        Collectors.summingDouble(Order::getTotalPrice)
                ))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("dateNow", entry.getKey());
                    result.put("totalPrice", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }

    public Double getTotalPriceOfCompletedOrders() {
        List<Order> completedOrders = orderRepository.findByStatus("Completed");
        Double totalPrice = 0.0;
        for (Order order : completedOrders) {
            totalPrice += order.getTotalPrice();
        }
        return totalPrice;
    }

    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }
}
