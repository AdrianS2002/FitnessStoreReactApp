package com.example.ProiectMagazinSuplimenteAlimentare.repository;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Order;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderRepository interface is used to create the methods that will be implemented in the OrderRepository class.

 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
//    Order findFirstByUser(User user);
    Order findFirstByOrderNumber(Long orderNumber);
}
