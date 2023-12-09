package com.cn.partnerservice.service;

import com.cn.partnerservice.models.Owner;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.repositories.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;
/**
 * Service class for owner entity.
 * Contains business logic.
 */
@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    /**
     * Constructor for owner service class.
     * @param ownerRepository
     */
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Gets a list of all Owners.
     * @return List of owners
     */
    public ResponseEntity getAllOwners() { return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.findAll()); }

    /**
     * Gets all stores belonging to owner.
     * @param ownerID
     * @return Set of stores
     */
    public ResponseEntity getAllStoresByOwner(int ownerID) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.findById(ownerID).getStores());
    }

    /**
     * Create a new owner.
     * @param newOwner
     * @return Owner object
     */
    public ResponseEntity createOwner(@RequestBody Owner newOwner) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.save(newOwner));
    }

    /**
     * Find by the account reference.
     * @param accountRef
     * @return Set of stores
     */
    public ResponseEntity getAllStoresByOwnerRef(int accountRef) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.findByAccountRef(accountRef).getStores());
    }
}
