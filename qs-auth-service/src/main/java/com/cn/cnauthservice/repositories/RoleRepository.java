package com.cn.cnauthservice.repositories;

import com.cn.cnauthservice.models.ERole;
import com.cn.cnauthservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Data access interface for role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Finds role by role name.
     * @param name
     * @return
     */
    Optional<Role> findByName(ERole name);
}