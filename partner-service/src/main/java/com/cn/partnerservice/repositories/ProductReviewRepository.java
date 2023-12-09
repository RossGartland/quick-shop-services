package com.cn.partnerservice.repositories;

import com.cn.partnerservice.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    @Transactional
    List<ProductReview> findAllByProductID(long productID);
}
