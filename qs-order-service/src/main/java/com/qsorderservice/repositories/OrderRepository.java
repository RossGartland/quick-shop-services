package com.qsorderservice.repositories;

import com.qsorderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Data access interface for order entity.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStoreID(long id);
    Order findByOrderID(long id);
    List<Order> findAllByCustomerID(long id);

}
