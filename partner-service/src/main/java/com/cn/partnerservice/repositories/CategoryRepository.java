package com.cn.partnerservice.repositories;
import com.cn.partnerservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access interface for category entity.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}