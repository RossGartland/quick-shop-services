package com.cn.partnerservice.controllers;


import com.cn.partnerservice.models.StoreReview;
import com.cn.partnerservice.pojo.LocationRequest;
import com.cn.partnerservice.service.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stores")
public class StoreReviewController {

    @Autowired
    StoreReviewService storeReviewService;

    public StoreReviewController(StoreReviewService storeReviewService) {
        this.storeReviewService = storeReviewService;
    }

    @PostMapping(path = "/reviews")
    public ResponseEntity createStoreReview(@RequestBody StoreReview storeReview) {
        return this.storeReviewService.createStoreReview(storeReview);
    }

    @PutMapping(path = "/reviews")
    public ResponseEntity editStoreReview(@RequestBody StoreReview storeReview) {
        return this.storeReviewService.editStoreReview(storeReview);
    }

    @GetMapping(path = "/{storeID}/reviews")
    public ResponseEntity getStoresReviews(@PathVariable int storeID) { return this.storeReviewService.getStoresReviews(storeID);}
}
