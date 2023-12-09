package com.cn.partnerservice.controllers;

import com.cn.partnerservice.models.Owner;
import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
/**
 * API Endpoints for owner entity.
 */
@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * Constructor method for the owner controller.
     */
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Gets a list of all owners.
     * @return owners list.
     */
    @GetMapping
    public ResponseEntity getAllOwners() { return this.ownerService.getAllOwners();}

    /**
     * Gets all stores belonging to an owner.
     * @param ownerID This is the ID corresponding to the ownerID in the database.
     * @return set stores.
     */
    @GetMapping(path = "/{ownerID}/stores")
    public ResponseEntity getAllStoresByOwner(@PathVariable int ownerID) {
        return this.ownerService.getAllStoresByOwner(ownerID);
    }

    /**
     * Creates a new owner using the values sent in the request body.
     * @param newOwner
     * @return Owner object This is the new owner that has been created.
     */
    @PostMapping
    public ResponseEntity createOwner(@RequestBody Owner newOwner) { return ownerService.createOwner(newOwner);}

    /**
     * Gets stores corresponding to the account ID from the auth-service.
     * @param accountRef
     * @return Set store This is the set of stores belonging to the user.
     */
    @GetMapping(path = "/ref/{accountRef}/stores")
    public ResponseEntity getAllStoresByOwnerRef(@PathVariable int accountRef) {
        return this.ownerService.getAllStoresByOwnerRef(accountRef);
    }
}
