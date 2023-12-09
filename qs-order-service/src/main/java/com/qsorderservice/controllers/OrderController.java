package com.qsorderservice.controllers;

import com.qsorderservice.models.Order;
import com.qsorderservice.pojo.OrderRequest;
import com.qsorderservice.repositories.OrderRepository;
import com.qsorderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  API Endpoints for order entity.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * Returns all orders.
     * @return
     */
    @GetMapping()
    public List<Order> getAllOrders() { return this.orderService.getAllOrders();}

    /**
     * Creates a new order.
     * @param orderRequest
     * @return
     */
    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) { return this.orderService.createOrder(orderRequest);}

    /**
     * Finds all orders belonging to a given store.
     * @param storeID
     * @return
     */
    @GetMapping("/store/{storeID}")
    public ResponseEntity findAllStoreOrders(@PathVariable long storeID) { return this.orderService.findAllStoreOrders(storeID);}

    /**
     * Finds all orders belonging to a given customer.
     * @param customerID
     * @return
     */
    @GetMapping("/customers/{customerID}")
    public ResponseEntity getCustomerOrders(@PathVariable long customerID) { return this.orderService.getCustomerOrders(customerID);}

    /**
     * Gets a single order corresponding to a given orderID.
     * @param orderID
     * @return
     */
    @GetMapping("/{orderID}")
    public ResponseEntity getOrder(@PathVariable long orderID) { return this.orderService.getOrder(orderID);}

    /**
     * Returns all orders belonging to a specified customer.
     * @param orderID
     * @param status
     * @return
     */
    @PatchMapping("/{orderID}/status")
    public ResponseEntity changeStatus(@PathVariable long orderID, @RequestBody String status) { return this.orderService.changeStatus(orderID, status);}

    /**
     * Retrieves store statistics.
     * @param storeID
     * @return
     */
    @GetMapping("/store/{storeID}/stats")
    public ResponseEntity retrieveOrderStats(@PathVariable long storeID) { return this.orderService.retrieveOrderStats(storeID);}
}
