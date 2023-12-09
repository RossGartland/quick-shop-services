package com.cn.partnerservice.service;

import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.models.StoreReview;
import com.cn.partnerservice.repositories.StoreRepository;
import com.cn.partnerservice.repositories.StoreReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreReviewService {
    @Autowired
    StoreReviewRepository storeReviewRepository;
    @Autowired
    StoreRepository storeRepository;

    public StoreReviewService(StoreReviewRepository storeReviewRepository) {
        this.storeReviewRepository = storeReviewRepository;
    }

    public ResponseEntity createStoreReview(StoreReview storeReview) {
        storeReviewRepository.save(storeReview);
        determineStoreRating(storeReview.getStoreID());
        return ResponseEntity.status(HttpStatus.CREATED).body(storeReview);
    }

    public ResponseEntity editStoreReview(StoreReview storeReview) {
        storeReviewRepository.save(storeReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeReview);
    }
    public ResponseEntity getStoresReviews(long storeID) {
        List<StoreReview> storeReviews = storeReviewRepository.findAllByStoreID(storeID);
        return ResponseEntity.status(HttpStatus.OK).body(storeReviews);
    }

    public void determineStoreRating(long storeID) {
        List<StoreReview> storeReviews = storeReviewRepository.findAllByStoreID(storeID);
        int averageStoreRating = 0;
        int totalStoreRating = 0;
        for (StoreReview storeReview: storeReviews
             ) {
            totalStoreRating += storeReview.getRating();
        }
        averageStoreRating = totalStoreRating / storeReviews.size();

        Store store = storeRepository.findById(storeID);
        store.setStoreRating(averageStoreRating);
        storeRepository.save(store);
    }
}
