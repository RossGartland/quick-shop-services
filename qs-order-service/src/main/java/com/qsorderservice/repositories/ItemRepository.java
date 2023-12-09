package com.qsorderservice.repositories;

import com.qsorderservice.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Data access interface for item entity.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
    Set<Item> findAllByOrderID(long id);
    Set<Item> findByOrderID(long id);
}
