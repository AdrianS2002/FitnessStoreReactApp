package com.example.ProiectMagazinSuplimenteAlimentare.repository;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * RoleRepository interface is used to create the methods that will be implemented in the RoleRepository class.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
        Role findByRole(String role);
}
