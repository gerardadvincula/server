package com.cavitestate.ecommerce.controller;

import com.cavitestate.ecommerce.dto.OrderDto;
import com.cavitestate.ecommerce.model.Order;
import com.cavitestate.ecommerce.model.User;
import com.cavitestate.ecommerce.repository.UserRepository;
import com.cavitestate.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/create")
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Optional<User> optionalUser = userRepository.findById(orderDto.getUserId());
        optionalUser.ifPresent(appUser -> orderService.createOrder(orderDto, appUser));
        return orderDto;
    }

    @GetMapping("/list")
    public List<Order> getListOrder() {
        return orderService.getListOrder();
    }

    @GetMapping("/list/{orderId}")
    private Order getOrderById(@PathVariable("orderId") String orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/updateOrderList/{orderId}")
    public Order updateOrderById(@PathVariable("orderId") String orderId, @RequestBody Order order) {
        orderService.updateOrderById(orderId, order);
        return order;
    }

    @PutMapping("/update/status/{orderId}")
    public Order updateStatusById(@PathVariable("orderId") String orderId, @RequestBody Order order) {
        orderService.updateStatusById(orderId, order);
        return order;
    }

    @PutMapping("/update/proofPayment/{orderId}")
    public Order updateProofPaymentById(@PathVariable("orderId") String orderId, @RequestBody Order order) {
        orderService.updateProofPaymentById(orderId, order);
        return order;
    }

    @DeleteMapping("/delete/{orderId}")
    private String deleteOrderById(@PathVariable("orderId") String orderId, @RequestBody Order order) {
        orderService.deleteOrderById(orderId, order);
        return "order deleted";
    }

//    @GetMapping("/list-sales")
//    private List<OrderRepository.sumOfTotalPrice> getByDayPrice() {
//        return orderService.priceByDay();
//    }

    @GetMapping("/list-sales")
    List<Map<String, Object>> getTotalSalesPerMonth() {
        return orderService.getTotalSalesPerMonth();
    }

    @GetMapping("/total-price")
    public ResponseEntity<Double> getTotalPriceOfCompletedOrders() {
        Double totalPrice = orderService.getTotalPriceOfCompletedOrders();
        return ResponseEntity.ok(totalPrice);
    }

    @GetMapping("/listOrder/{email}")
    private ResponseEntity<List<Order>> getOrderListByEmail(@PathVariable String email){
       List<Order> orderListByEmail = orderService.getOrdersByEmail(email);
        return ResponseEntity.ok(orderListByEmail);
    }

}
