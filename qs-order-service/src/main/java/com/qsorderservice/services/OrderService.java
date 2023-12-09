package com.qsorderservice.services;

import com.qsorderservice.models.EStatus;
import com.qsorderservice.models.Item;
import com.qsorderservice.models.Order;
import com.qsorderservice.models.EmailDetails;
import com.qsorderservice.pojo.OrderRequest;
import com.qsorderservice.pojo.StatRequest;
import com.qsorderservice.repositories.ItemRepository;
import com.qsorderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Service class for order entity.
 * Contains business logic.
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    private EmailService emailService;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Returns all orders in the system.
     * @return
     */
    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    /**
     * Creates a new order.
     * @param orderRequest
     * @return
     */
    public ResponseEntity createOrder(OrderRequest orderRequest) {
        Order newOrder = new Order(); //Create order
        newOrder.setCustomerID(orderRequest.customerID);
        newOrder.setStoreID(orderRequest.storeID);
        newOrder.setCustomerEmail(orderRequest.getCustomerEmail());
        newOrder.setTotalItemCost(orderRequest.totalItemCost);
        newOrder.setIsDelivery(orderRequest.isDelivery);
        newOrder.setCollection(orderRequest.isCollection);
        newOrder.setDeliveryFee(orderRequest.deliveryFee);
        newOrder.setTotalCost(orderRequest.totalCost);
        newOrder.setStatus(EStatus.SUBMITTED);
        newOrder.setDeliveryAddress(orderRequest.getDeliveryAddress());
        newOrder.setCustomerLat(orderRequest.getCustomerLat());
        newOrder.setCustomerLng(orderRequest.getCustomerLng());
        newOrder = orderRepository.save(newOrder);
        orderRequest.setOrderID(newOrder.getOrderID());
        orderRequest.setStatus(newOrder.getStatus());

        Item item = new Item(); //Create Items
        for (Item i: orderRequest.items
             ) {
            i.setOrderID(newOrder.getOrderID());
            itemRepository.save(i);
            orderRequest.items.add(i);
        }
        EmailDetails emailDetails = new EmailDetails(newOrder.getCustomerEmail(), "Thank you for submitting an order with us." +
                "Your order is currently being processed." +
                "Remember to check your order status on the orders page to keep informed.", "Quick Shop Order Number: " +newOrder.getOrderID());
        emailService.sendSimpleMail(emailDetails);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderRequest);
    }

    /**
     * Finds all orders belonging to a given store.
     * @param storeID
     * @return
     */
    public ResponseEntity findAllStoreOrders(long storeID) {

        List<Order> orders = orderRepository.findAllByStoreID(storeID);
        Set<Item> items;
        List<OrderRequest> storeOrders = new ArrayList<>();
        for (Order o: orders
             ) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setCustomerID(o.getCustomerID());
            orderRequest.setStoreID(o.getStoreID());
            orderRequest.setTotalItemCost(o.getTotalCost());
            orderRequest.setIsDelivery(o.isDelivery());
            orderRequest.setCollection(o.isCollection());
            orderRequest.setDeliveryFee(o.getDeliveryFee());
            orderRequest.setDeliveryAddress(o.getDeliveryAddress());
            orderRequest.setTotalCost(o.getTotalCost());
            orderRequest.setOrderID(o.getOrderID());
            orderRequest.setStatus(o.getStatus());
            orderRequest.setCustomerLat(o.getCustomerLat());
            orderRequest.setCustomerLng(o.getCustomerLng());

            items = itemRepository.findAllByOrderID(o.getOrderID());
            orderRequest.setItems(items);

            storeOrders.add(orderRequest);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(storeOrders);
    }

    /**
     * Gets a single order corresponding to a given orderID.
     * @param orderID
     * @return
     */
    public ResponseEntity getOrder(long orderID) {

        Order order = orderRepository.findByOrderID(orderID);
        Set<Item> items = itemRepository.findByOrderID(orderID);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomerID(order.getCustomerID());
        orderRequest.setStoreID(order.getStoreID());
        orderRequest.setTotalItemCost(order.getTotalCost());
        orderRequest.setIsDelivery(order.isDelivery());
        orderRequest.setCollection(order.isCollection());
        orderRequest.setDeliveryFee(order.getDeliveryFee());
        orderRequest.setTotalCost(order.getTotalCost());
        orderRequest.setDeliveryAddress(order.getDeliveryAddress());
        orderRequest.setOrderID(order.getOrderID());
        orderRequest.setStatus(order.getStatus());
        orderRequest.setCustomerLat(order.getCustomerLat());
        orderRequest.setCustomerLng(order.getCustomerLng());
        orderRequest.setItems(items);


        return ResponseEntity.status(HttpStatus.OK)
                .body(orderRequest);
    }

    /**
     * Returns all orders belonging to a specified customer.
     * @param customerID
     * @return
     */
    public ResponseEntity getCustomerOrders(long customerID) {

        List<Order> orders = orderRepository.findAllByCustomerID(customerID);
        Set<Item> items;
        List<OrderRequest> customerOrders = new ArrayList<>();
        for (Order o: orders
        ) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setCustomerID(o.getCustomerID());
            orderRequest.setStoreID(o.getStoreID());
            orderRequest.setTotalItemCost(o.getTotalCost());
            orderRequest.setIsDelivery(o.isDelivery());
            orderRequest.setCollection(o.isCollection());
            orderRequest.setDeliveryFee(o.getDeliveryFee());
            orderRequest.setTotalCost(o.getTotalCost());
            orderRequest.setOrderID(o.getOrderID());
            orderRequest.setStatus(o.getStatus());
            orderRequest.setDeliveryAddress(o.getDeliveryAddress());
            orderRequest.setCustomerLat(o.getCustomerLat());
            orderRequest.setCustomerLng(o.getCustomerLng());

            items = itemRepository.findAllByOrderID(o.getOrderID());
            orderRequest.setItems(items);

            customerOrders.add(orderRequest);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerOrders);
    }


    /**
     * Changes the order status.
     * @param orderID
     * @param newStatus
     * @return
     */
    public ResponseEntity changeStatus(long orderID, String newStatus) {

        Order order = orderRepository.findByOrderID(orderID);

        switch(newStatus) {
            case "CANCELLED":
                order.setStatus(EStatus.CANCELLED);
                break;
            case "SUBMITTED":
                order.setStatus(EStatus.SUBMITTED);
                break;
            case "PREPARING":
                order.setStatus(EStatus.PREPARING);
                break;
            case "OUT_FOR_DELIVERY":
                order.setStatus(EStatus.OUT_FOR_DELIVERY);
                break;
            case "DELIVERED":
                order.setStatus(EStatus.DELIVERED);
                break;
            case "READY_FOR_COLLECTION":
                order.setStatus(EStatus.READY_FOR_COLLECTION);
                break;
            case "COLLECTED":
                order.setStatus(EStatus.COLLECTED);
                break;
            case "COMPLETE":
                order.setStatus(EStatus.COMPLETE);
                EmailDetails emailDetails = new EmailDetails(order.getCustomerEmail(), "You order is now complete." +
                        "Thank you for shopping with us.", "Quick Shop Order Number: " +order.getOrderID());
                emailService.sendSimpleMail(emailDetails);
                break;
        }
        orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Status has been updated to: " + order.getStatus());
    }

    /**
     * Retrieves store statistics.
     * @param storeID
     * @return
     */
    public ResponseEntity retrieveOrderStats(long storeID) {

        List<Order> orders = orderRepository.findAllByStoreID(storeID);

        Set<Item> items;
        BigDecimal revenue = new BigDecimal(0.00);
        int totalOrders = orders.size();
        int productsSold = 0;
        List<OrderRequest> storeOrders = new ArrayList<>();
        for (Order o: orders
        ) {
            revenue = revenue.add(o.getTotalCost());
            items = itemRepository.findAllByOrderID(o.getOrderID());
            productsSold += items.size();
        }

        StatRequest statRequest = new StatRequest(revenue,totalOrders,productsSold);

        return ResponseEntity.status(HttpStatus.OK)
                .body(statRequest);
    }
}
