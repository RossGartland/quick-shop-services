package com.cn.partnerservice.service;

import com.cn.partnerservice.helpers.BlobHelper;
import com.cn.partnerservice.helpers.DistanceHelper;
import com.cn.partnerservice.models.Owner;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.pojo.LocationRequest;
import com.cn.partnerservice.pojo.StoreRequest;
import com.cn.partnerservice.repositories.OwnerRepository;
import com.cn.partnerservice.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Service class for store entity.
 * Contains business logic.
 */
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    BlobHelper blobHelper;
    @Autowired
    DistanceHelper distanceHelper;
    @Autowired
    OwnerRepository ownerRepository;

    /**
     * Constructor for store service class.
     * @param storeRepository
     */
    public StoreService(StoreRepository storeRepository) {this.storeRepository = storeRepository;}

    /**
     * Gets a list of all Partners.
     * @return
     */
    public List<Store> getAllStores() { return storeRepository.findAll(); }

    /**
     * Gets a store corresponding to the provided ID/
     * @param storeID
     * @return
     */
    public Store getStoreByID(int storeID) {return storeRepository.findById(storeID);}

    /**
     * Gets all owners of a particular store.
     * @param storeID
     * @return
     */
    public Set<Owner> getAllOwnersOfAStore(int storeID) {return storeRepository.findById(storeID).getOwners();}

    /**
     * Creates a new store owner.
     * @param storeRequest
     * @return
     */
    public ResponseEntity createPartner(StoreRequest storeRequest, MultipartFile file) throws IOException {
        try {
            Store store = new Store(); //Create store object
            store.setStoreName(storeRequest.storeName);
            store.setPhoneNumber(storeRequest.phoneNumber);
            store.setEmailAddress(storeRequest.emailAddress);
            store.setAddress(storeRequest.address);
            store.setLatitude(storeRequest.latitude);
            store.setLongitude(storeRequest.longitude);
            store.setDeliveryFee(storeRequest.deliveryFee);
            store.setStoreImagePath(blobHelper.storeFile(file.getOriginalFilename(), store.getStoreName(), file.getInputStream(), file.getSize()));
            store.setOwners(storeRequest.owners
                    .stream()
                    .map(owner -> {
                        Owner oowners = owner;
                        if (oowners.getAccountRef() > 0) {
                            oowners = ownerRepository.findByAccountRef(oowners.getAccountRef()); //Prevents duplicated owners and stores.
                        }
                        oowners.addStore(store);
                        return oowners;
                    })
                    .collect(Collectors.toSet())); //Maps the provided owners object to the owners class object.
            store.setIsActive(true);
            storeRepository.save(store);
            return ResponseEntity.status(HttpStatus.OK).body(store);
        }
        catch(NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    /**
     * Finds all stores within a 10-mile radius of the given longitude and latitude.
     * @param locationRequest
     * @return
     */
    public ResponseEntity findStoresByLatLng(LocationRequest locationRequest) {
        List<Store> storeList = storeRepository.findAll(); //Get all stores
        List<Store> nearbyStores = new ArrayList(); //Initialize an empty list to store valid stores.
        double distanceCalculated; //The distance between 2 points.
        for (Store store: storeList) {
            distanceCalculated = distanceHelper.distanceCalculation(store.getLatitude(), locationRequest.lat, store.getLongitude(), locationRequest.lng); //Calculate the distance.
            if(distanceCalculated < 10) {
                nearbyStores.add(store); //Add store to list if it is within a distance.
            }
        }
        if(nearbyStores.size()>0) {
            return ResponseEntity.status(HttpStatus.OK).body(nearbyStores);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry, there are no stores in your area.");
    }

    /**
     * Deletes a store corresponding to the provided store ID.
     * @param storeID
     * @return
     */
    public ResponseEntity deleteStore(long storeID) {
        storeRepository.deleteById(storeID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Store Deleted");
    }

    /**
     * Updates a store using a given store object.
     * @param store
     * @return
     */
    public ResponseEntity editStore(Store store) {
        storeRepository.editById(store.getStoreID(),store.getStoreName(),
                store.getPhoneNumber(), store.getEmailAddress(), store.getAddress(),
                store.getLatitude(), store.getLongitude(), store.getDeliveryFee());

        return ResponseEntity.status(HttpStatus.OK).body(store);
    }

    /**
     * Sets the status of a store to either inactive or active depending on the current status.
     * @param storeID
     * @return
     */
    public ResponseEntity toggleStoreActiveStatus(long storeID) {

        Store store = storeRepository.findById(storeID);

        if(store.isActive() == true) {
            store.setIsActive(false);
            storeRepository.save(store);
            return ResponseEntity.status(HttpStatus.OK).body("Store is now inactive.");
        }
        store.setIsActive(true);
        storeRepository.save(store);
        return ResponseEntity.status(HttpStatus.OK).body("Store is now active.");
    }
}
