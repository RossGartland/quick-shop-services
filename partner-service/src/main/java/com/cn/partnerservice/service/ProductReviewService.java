package com.cn.partnerservice.service;

import com.cn.partnerservice.models.Product;
import com.cn.partnerservice.models.ProductReview;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.models.StoreReview;
import com.cn.partnerservice.repositories.ProductRepository;
import com.cn.partnerservice.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewService {
    @Autowired
    ProductReviewRepository productReviewRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    public ResponseEntity createProductReview(ProductReview productReview) {
        productReviewRepository.save(productReview);
        determineProductRating(productReview.getProductID());
        return ResponseEntity.status(HttpStatus.CREATED).body(productReview);
    }

    public ResponseEntity editProductReview(ProductReview productReview) {
        productReviewRepository.save(productReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(productReview);
    }
    public ResponseEntity getProductsReviews(long productID) {
        List<ProductReview> productReviews = productReviewRepository.findAllByProductID(productID);
        return ResponseEntity.status(HttpStatus.OK).body(productReviews);
    }

    public void determineProductRating(long storeID) {
        List<ProductReview> productReviews = productReviewRepository.findAllByProductID(storeID);
        int averageProductRating = 0;
        int totalProductRating = 0;
        for (ProductReview productReview: productReviews
        ) {
            totalProductRating += productReview.getRating();
        }
        averageProductRating = totalProductRating / productReviews.size();

        Product product = productRepository.findById(storeID);
        product.setProductRating(averageProductRating);
        productRepository.save(product);
    }
}
