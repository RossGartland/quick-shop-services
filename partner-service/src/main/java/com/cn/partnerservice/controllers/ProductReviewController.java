package com.cn.partnerservice.controllers;

import com.cn.partnerservice.models.ProductReview;
import com.cn.partnerservice.models.StoreReview;
import com.cn.partnerservice.service.ProductReviewService;
import com.cn.partnerservice.service.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductReviewController {
    @Autowired
    ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @PostMapping(path = "/reviews")
    public ResponseEntity createProductReview(@RequestBody ProductReview productReview) {
        return this.productReviewService.createProductReview(productReview);
    }

    @PutMapping(path = "/reviews")
    public ResponseEntity editProductReview(@RequestBody ProductReview productReview) {
        return this.productReviewService.editProductReview(productReview);
    }

    @GetMapping(path = "/{productID}/reviews")
    public ResponseEntity getProductReviews(@PathVariable int productID) { return this.productReviewService.getProductsReviews(productID);}
}
