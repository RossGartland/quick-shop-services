package com.cn.partnerservice.controllers;

import com.cn.partnerservice.models.Product;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
/**
 * API Endpoints for product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    /**
     * Constructor method for the product controller.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all products in the system.
     * @ List product. This is the list of products.
     */
    @GetMapping
    public ResponseEntity getAllProducts() { return this.productService.getAllProducts();}

    /**
     * Creates a new product corresponding to the values in the request body.
     * @param newProduct
     * @return Product This returns the new product created.
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity addNewProduct(@RequestPart Product newProduct,@RequestPart MultipartFile file) throws IOException { return this.productService.addNewProduct(newProduct, file);}

    /**
     * Gets all products belonging to a store
     * @param storeID
     * @return Set product This returns set of products.
     */
    @GetMapping(path = "/store/{storeID}")
    public ResponseEntity getAllStoresProducts(@PathVariable long storeID) {
        return this.productService.getAStoresProducts(storeID);
    }

    /**
     * Gets a certain product corresponding to the ID
     * @param productID
     * @return Product This is the returned product.
     */
    @GetMapping(path = "/{productID}")
    public ResponseEntity getProductByID(@PathVariable int productID) { return this.productService.getProductByID(productID);}

    /**
     * Updates certain values of a particular product.
     * @param product
     * @return Product The updated product.
     */
    @PatchMapping
    public ResponseEntity editProduct(@RequestBody Product product) {
        return this.productService.editProduct(product);}

    /**
     * Deletes a product corresponding to the provided ID.
     * @param productID
     */
    @DeleteMapping(path = "/{productID}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long productID) { this.productService.deleteProduct(productID);}

    /**
     * Sends a request to the Brocade API and returns the corresponding product data.
     * @param productEAN
     * @return
     */
    @GetMapping(path = "brocade/{productEAN}")
    public ResponseEntity getBrocadeProduct(@PathVariable String productEAN) {
        String brocadePath = "https://www.brocade.io/api/items/";
        RestTemplate restTemplate = new RestTemplate(); //Create a Rest client to handle HTTP Request
        Object result = restTemplate.getForObject(brocadePath+productEAN, Object.class); //Send a get request to the given URI
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    /**
     * Sets the sale price of a product.
     * @param productID
     * @param salePrice
     */
    @PutMapping(path="/{productID}/sale")
    public void setProductOnSale(@PathVariable long productID, @RequestBody BigDecimal salePrice) { this.productService.setProductOnSale(productID, salePrice);}

    /**
     * Removes a product as a sale item.
     * @param productID
     */
    @DeleteMapping(path="/{productID}/sale")
    public void removeProductFromSale(@PathVariable long productID) { this.productService.removeProductFromSale(productID);}

    /**
     * Returns a list of products on sale from nearby stores.
     * @param storeList
     * @return
     */
    @PostMapping(path="/sales")
    public ResponseEntity findProductsOnSaleForNearbyStores(@RequestBody List<Store> storeList) {return this.productService.findProductsOnSaleForNearbyStores(storeList);}
}
