package com.cn.partnerservice.repositories;

import com.cn.partnerservice.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Data access interface for owner entity.
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

     /**
      * Find owner where id matches the provided id.
      * @param id
      * @return Owner object
      */
     Owner findById(long id);

     /**
      * Gets owner corresponding to the accountRef.
      * @param accountRef
      * @return
      */
     Owner findByAccountRef(int accountRef);
}