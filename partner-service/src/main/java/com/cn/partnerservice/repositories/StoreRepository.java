package com.cn.partnerservice.repositories;

import com.cn.partnerservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
/**
 * Data access interface for store entity.
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

     /**
      * Finds a store corresponding to the provided ID.
      * @param id
      * @return Store object
      */
     Store findById(long id);

     /**
      * Gets all stores that are owned by the provided ownerID.
      * @param ownerID
      * @return List of stores
      */
     List<Store> findAllByOwners(int ownerID);

     /**
      * Deletes a store by providing a specified store ID.
      * @param storeID
      */
     @Transactional
     @Modifying
     @Query("DELETE FROM Store s WHERE s.storeID = :storeID")
     void deleteById(@Param("storeID") Long storeID);

     /**
      * Edits a store by providing a store object.
      * @param storeID
      * @param storeName
      * @param phoneNumber
      * @param emailAddress
      * @param address
      * @param latitude
      * @param longitude
      * @param deliveryFee
      */
     @Transactional
     @Modifying
     @Query("UPDATE Store s SET s.storeName = :storeName, s.phoneNumber = :phoneNumber, s.emailAddress = :emailAddress, s.address = :address," +
             "s.latitude = :latitude, s.longitude = :longitude, s.deliveryFee = :deliveryFee WHERE s.storeID = :storeID")
     void editById(@Param("storeID") Long storeID, @Param("storeName") String storeName,
                   @Param("phoneNumber") String phoneNumber,@Param("emailAddress") String emailAddress,
                   @Param("address") String address, @Param("latitude") double latitude,
                   @Param("longitude") double longitude,@Param("deliveryFee") BigDecimal deliveryFee);

}
