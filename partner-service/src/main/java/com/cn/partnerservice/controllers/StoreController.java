package com.cn.partnerservice.controllers;

import com.cn.partnerservice.models.Owner;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.pojo.LocationRequest;
import com.cn.partnerservice.pojo.StoreRequest;
import com.cn.partnerservice.repositories.OwnerRepository;
import com.cn.partnerservice.repositories.StoreRepository;
import com.cn.partnerservice.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.util.List;
import java.util.Set;
/**
 * API Endpoints for store entity.
 */
@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    StoreRepository storeRepository;

    private final StoreService storeService;

    /**
     * Constructor method for the store controller.
     * @param storeService
     */
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Gets all stores in the database.
     * @return List store
     */
    @GetMapping
    public List<Store> getAllStore() { return this.storeService.getAllStores();}

    /**
     * Gets a specific store corresponding to the storeID.
     * @param storeID
     * @return Store object.
     */
    @GetMapping(path = "/{storeID}")
    public Store getStoreByID(@PathVariable int storeID) { return this.storeService.getStoreByID(storeID);}

    /**
     * Gets all owners of a particular store.
     * @param storeID
     * @return Set Owner
     */
    @GetMapping(path = "/{storeID}/owners")
    public Set<Owner> getAllOwnersOfAStore(@PathVariable int storeID) {
        return this.storeService.getAllOwnersOfAStore(storeID);
    }

    /**
     * Creates a new store.
     * @param storeRequest
     * @return Store object. Returns the new store.
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity createStore(@RequestPart StoreRequest storeRequest, @RequestPart MultipartFile file) throws IOException { return storeService.createPartner(storeRequest, file);}

    /**
     * Returns a list of stores within a specified radius of a given latitude and longitude.
     * @param locationRequest
     * @return
     */
    @PostMapping(path = "/searchmap")
    public ResponseEntity findStoreByLatLong(@RequestBody LocationRequest locationRequest) {
        return this.storeService.findStoresByLatLng(locationRequest);
    }

    /**
     * Deletes a store.
     * @param storeID
     * @return
     */
    @DeleteMapping(path = "/{storeID}")
    public ResponseEntity deleteStore(@PathVariable long storeID) { return this.storeService.deleteStore(storeID);}

    /**
     * Edits a store by providing a store object.
     * @param storeID
     * @param store
     * @return
     */
    @PutMapping(path = "/{storeID}")
    public ResponseEntity editStore(@PathVariable long storeID, @RequestBody Store store) { return this.storeService.editStore(store);}

    /**
     * Sets the status of a store to either inactive or active depending on the current status.
     * @param storeID
     * @return
     */
    @PutMapping(path="{storeID}/status")
    public ResponseEntity toggleStoreActiveStatus(@PathVariable long storeID) {return  this.storeService.toggleStoreActiveStatus(storeID);}

}

