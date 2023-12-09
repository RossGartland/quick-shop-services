package com.cn.partnerservice.repositories;
import com.cn.partnerservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
/**
 * Data access interface for product entity.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds a product corresponding to the provided ID.
     * @param id
     * @return Product object
     */
    Product findById(long id);

    /**
     * Selects all products where the storeID in the database matches the provided id.
     * @param storeID
     * @return Set products
     */
    @Query("select p from Product p where p.storeID = :storeid")
    Set<Product> findByStoreID(@Param("storeid") long storeID);

    /**
     * Find products on sale in a particular store.
     * @param storeID
     * @return
     */
    @Query("select p from Product p where p.storeID = :storeid AND p.isOnSale = true")
    List<Product> findSaleProductsByStoreID(@Param("storeid") long storeID);

    /**
     * Deletes a product where productID matches the provided id.
     * @param id
     */
    @Modifying
    @Query("DELETE FROM Product p where p.productID = :id")
    void deleteById(long id);
}
