package com.example.ProiectMagazinSuplimenteAlimentare.repository;


import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * UserRepository interface is used to create the methods that will be implemented in the UserRepository class.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstByUsername(String username);
    User findFirstByName(String nume);
    User findFirstByUsernameAndPassword(String username, String password);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.flag = TRUE WHERE u.username = :username")
    void updateLoginFlag(String username);
}