package com.example.ProiectMagazinSuplimenteAlimentare.service.order;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderProductDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderUserDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Order;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * OrderService interface is used to create the methods that will be implemented in the OrderServiceImpl class.

 */
@Component
public interface OrderService{

    List<Order> findAll();
    Order findOrderById(Long id);
    OrderDTO saveOrder(OrderCreationDTO order);
    OrderDTO updateOrder(OrderDTO order);
    void addProduct(Order order, Product product);
     Order getTotalPrice(Order order);

    Order findFirstByOrderNumber(Long orderNumber);
    boolean deleteOrder(Long id);
    Order assignProduct(OrderProductDTO dto);


}
