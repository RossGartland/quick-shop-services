package com.cn.partnerservice.service;

import com.cn.partnerservice.helpers.BlobHelper;
import com.cn.partnerservice.models.Product;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.pojo.LocationRequest;
import com.cn.partnerservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for product entity.
 * Contains business logic.
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    BlobHelper blobHelper;

    /**
     * Constructor for product service class.
     * @param productRepository
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Gets all products.
     * @return
     */
    public ResponseEntity getAllProducts() { return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll()); }

    /**
     * Creates a new product.
     * @param newProduct
     * @param file
     * @return
     * @throws IOException
     */
    public ResponseEntity addNewProduct(Product newProduct, MultipartFile file) throws IOException {
        try {
            newProduct.setProductImagePath(blobHelper.storeProductImage(file.getOriginalFilename(), file.getInputStream(), file.getSize()));
            newProduct.setIsActive(true);
            newProduct.setIsOnSale(false);
            return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(newProduct));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Gets all products belonging to a particular store.
     * @param storeID
     * @return
     */
    public ResponseEntity getAStoresProducts(long storeID) {return ResponseEntity.status(HttpStatus.OK).body(productRepository.findByStoreID(storeID));}

    /**
     * Gets a product corresponding to provided ID.
     * @param productID
     * @return
     */
    public ResponseEntity getProductByID(int productID) {return ResponseEntity.status(HttpStatus.OK).body(productRepository.findById(productID));}

    /**
     * Edits a product with values provided by the response body.
     * @param product
     * @return
     */
    public ResponseEntity editProduct(Product product) {return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product));}

    /**
     * Delete a product corresponding to the provided ID.
     * @param productID
     */
    public void deleteProduct(long productID) {productRepository.deleteById(productID);}

    /**
     * Sets the sale price of a product.
     * @param productID
     * @param salePrice
     * @return
     */
    public ResponseEntity setProductOnSale(long productID, BigDecimal salePrice) {
        Product product = productRepository.findById(productID);
        //If product is not currently on sale, set the sale price and isOnSale values.
        if(product.isOnSale() == false) {
            product.setIsOnSale(true);
            product.setSalePrice(salePrice);
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product is now on sale.");
        }
        //if product is on sale, set the sale price with the new sale price.
        product.setSalePrice(salePrice);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body("Sale price has been updated.");
    }

    /**
     * Removes a product as a sale item.
     * @param productID
     * @return
     */
    public ResponseEntity removeProductFromSale(long productID) {
        Product product = productRepository.findById(productID);
        if(product.isOnSale() == true) {
            product.setIsOnSale(false);
            product.setSalePrice(null);
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product is no longer on sale.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product was not on sale.");
    }

    /**
     * Returns a list of products on sale from nearby stores.
     * @param storeList
     * @return
     */
    public ResponseEntity findProductsOnSaleForNearbyStores(List<Store> storeList) {

        List<Product> productList = new ArrayList(); //List to store products.

        //For each store in the store list, find products on sale.
        for (Store store: storeList
             ) {
            productList.addAll(productRepository.findSaleProductsByStoreID(store.getStoreID()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
    }



