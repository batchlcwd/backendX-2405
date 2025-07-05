package com.substring.irctc.repositories;

import com.substring.irctc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,String> {

    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
