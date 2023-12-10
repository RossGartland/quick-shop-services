package com.cn.partnerservice.service;

import com.cn.partnerservice.helpers.BlobHelper;
import com.cn.partnerservice.helpers.DistanceHelper;
import com.cn.partnerservice.models.Category;
import com.cn.partnerservice.models.Owner;
import com.cn.partnerservice.models.Product;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.repositories.ProductRepository;
import com.cn.partnerservice.repositories.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    static List<Product> products;
    static Product product;
    static List<Store> storeList;

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    private BlobHelper blobHelper;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        Category category = new Category();
        product = new Product(1L, "Product Name", "Brand Name", "Product Description",
                category, BigDecimal.valueOf(99.99), 100L, 1L, "12345678901234",
                "image/path", true, false, BigDecimal.valueOf(79.99),
                4, Date.valueOf("1000-01-01"), Date.valueOf("1000-01-01"));
        products = Arrays.asList(product);

        Owner owner = new Owner();
        Set owners = new HashSet<Owner>();
        owners.add(owner);
        storeList = Arrays.asList(
                new Store(1,"Store One","01632 945732",
                        "testStore@quickshop.com",
                        "address", owners, 40.7129, -74.0059, BigDecimal.valueOf(5),
                        "/testpath", true, 5, Date.valueOf("1000-01-01"),Date.valueOf("1000-01-01"))
        );
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(products);

        ResponseEntity response = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void addNewProduct() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "file-content".getBytes());

        String imagePath = "path/to/image.jpg";
        when(blobHelper.storeProductImage(anyString(), any(InputStream.class), anyLong())).thenReturn(imagePath);

        when(productRepository.save(product)).thenReturn(product);

        ResponseEntity response = productService.addNewProduct(product, file);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void editProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ResponseEntity response = productService.editProduct(product);

        verify(productRepository, times(1)).save(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void deleteProduct() {
        long productIdToDelete = 1L;
        productService.deleteProduct(productIdToDelete);
        verify(productRepository, times(1)).deleteById(productIdToDelete);
    }

    @Test
    void setProductOnSale() {
        long productID = 1L;
        BigDecimal salePrice = new BigDecimal("50.00");

        Product productNotOnSale = new Product();
        productNotOnSale.setProductID(productID); // Assuming setId method exists to set the product ID
        productNotOnSale.setIsOnSale(false); // Assume setIsOnSale method exists
        when(productRepository.findById(productID)).thenReturn(productNotOnSale);

        ResponseEntity response = productService.setProductOnSale(productID, salePrice);

        verify(productRepository, times(1)).save(productNotOnSale);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product is now on sale.", response.getBody());
    }
    @Test
    void removeProductFromSale() {
        long productID = 1L;

        Product productNotOnSale = new Product();
        productNotOnSale.setProductID(productID);
        productNotOnSale.setIsOnSale(false);
        when(productRepository.findById(productID)).thenReturn(productNotOnSale);

        ResponseEntity response = productService.removeProductFromSale(productID);

        verify(productRepository, never()).save(productNotOnSale);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product was not on sale.", response.getBody());
    }

    @Test
    void findProductsOnSaleForNearbyStores() {
        when(productRepository.findSaleProductsByStoreID(1L)).thenReturn(products);

        ResponseEntity response = productService.findProductsOnSaleForNearbyStores(storeList);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.addAll(products);

        verify(productRepository, times(1)).findSaleProductsByStoreID(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProducts, response.getBody());
    }
}