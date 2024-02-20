package com.lisko.repository;

import com.lisko.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
