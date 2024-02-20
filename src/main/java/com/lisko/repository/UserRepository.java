package com.lisko.repository;

import com.lisko.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/29/24
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}
