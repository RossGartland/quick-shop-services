package com.cn.partnerservice.service;

import com.cn.partnerservice.helpers.DistanceHelper;
import com.cn.partnerservice.models.Owner;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.pojo.LocationRequest;
import com.cn.partnerservice.pojo.StoreRequest;
import com.cn.partnerservice.repositories.StoreRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    static List<Store> stores;
    static Store storeOne;

    @InjectMocks
    StoreService storeService;

    @Mock
    StoreRepository storeRepository;

    @Mock
    private DistanceHelper distanceHelper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        stores = new ArrayList<>();
        Owner owner = new Owner();
        Set owners = new HashSet<Owner>();
        owners.add(owner);

        storeOne = new Store(1,"Store One","01632 945732",
                "testStore@quickshop.com",
                "address", owners, 40.7129, -74.0059, BigDecimal.valueOf(5),
                "/testpath", true, 5, Date.valueOf("1000-01-01"),Date.valueOf("1000-01-01") );
        Store storeTwo = new Store(2,"Store One","01632 945732",
                "testStore@quickshop.com",
                "address", owners, 40, 40, BigDecimal.valueOf(5),
                "/testpath", true, 5, Date.valueOf("1000-01-01"),Date.valueOf("1000-01-01") );
        Store storeThree = new Store(3,"Store One","01632 945732",
                "testStore@quickshop.com",
                "address", owners, 40, 40, BigDecimal.valueOf(5),
                "/testpath", true, 5, Date.valueOf("1000-01-01"),Date.valueOf("1000-01-01") );

        stores.add(storeOne);
        stores.add(storeTwo);
        stores.add(storeThree);
    }

    @Test
    void getAllStores() {
        when(storeRepository.findAll()).thenReturn(stores);
        List<Store> storeServiceList = storeService.getAllStores();
        assertEquals(3, storeServiceList.size());
        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void getStoreByID() {
        when(storeRepository.findById(1)).thenReturn(storeOne);
        Store storeServiceResponse = storeService.getStoreByID(1);
        assertEquals("Store One", storeServiceResponse.getStoreName());
        assertEquals("testStore@quickshop.com", storeServiceResponse.getEmailAddress());
        assertEquals(BigDecimal.valueOf(5), storeServiceResponse.getDeliveryFee());
        verify(storeRepository, times(1)).findById(1);
    }

    @Test
    void findStoresByLatLng() {
        LocationRequest locationRequest = new LocationRequest(40.7128, -74.0060);

        when(storeRepository.findAll()).thenReturn(stores);

        when(distanceHelper.distanceCalculation(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(5.0);

        ResponseEntity response = storeService.findStoresByLatLng(locationRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stores, response.getBody());
    }

    @Test
    void deleteStore() {
        long storeIdToDelete = 3;

        ResponseEntity response = storeService.deleteStore(storeIdToDelete);

        verify(storeRepository, times(1)).deleteById(storeIdToDelete);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Store Deleted", response.getBody());
    }

    @Test
    void editStore() {
        Store editedStore = new Store();
        editedStore.setStoreID(1);

        ResponseEntity response = storeService.editStore(editedStore);

        verify(storeRepository, times(1)).editById(
                editedStore.getStoreID(),
                editedStore.getStoreName(),
                editedStore.getPhoneNumber(),
                editedStore.getEmailAddress(),
                editedStore.getAddress(),
                editedStore.getLatitude(),
                editedStore.getLongitude(),
                editedStore.getDeliveryFee()
        );

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(editedStore, response.getBody());
    }

    @Test
    public void testToggleStoreActiveStatus_StoreIsActive() {
        // Create a sample active store object
        Store activeStore = new Store();
        activeStore.setStoreID(1);
        activeStore.setIsActive(true);

        // Mock behavior for findById
        when(storeRepository.findById(activeStore.getStoreID())).thenReturn(activeStore);

        // Test the method for an active store
        ResponseEntity response = storeService.toggleStoreActiveStatus(activeStore.getStoreID());

        // Verify that save was called with the modified active status
        verify(storeRepository, times(1)).save(activeStore);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Store is now inactive.", response.getBody());
    }

    @Test
    public void testToggleStoreActiveStatus_StoreIsInactive() {
        // Create a sample inactive store object
        Store inactiveStore = new Store();
        inactiveStore.setStoreID(2);
        inactiveStore.setIsActive(false);

        // Mock behavior for findById
        when(storeRepository.findById(inactiveStore.getStoreID())).thenReturn(inactiveStore);

        // Test the method for an inactive store
        ResponseEntity response = storeService.toggleStoreActiveStatus(inactiveStore.getStoreID());

        // Verify that save was called with the modified active status
        verify(storeRepository, times(1)).save(inactiveStore);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Store is now active.", response.getBody());
    }
}