package com.cn.partnerservice.repositories;

import com.cn.partnerservice.models.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
    @Transactional
    List<StoreReview> findAllByStoreID(long storeID);
}
