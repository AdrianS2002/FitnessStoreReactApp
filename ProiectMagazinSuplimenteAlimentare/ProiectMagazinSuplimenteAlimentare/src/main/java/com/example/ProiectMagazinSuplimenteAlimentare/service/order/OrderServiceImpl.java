package com.example.ProiectMagazinSuplimenteAlimentare.service.order;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderProductDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderUserDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.OrderMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Order;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductTag;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.OrderRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  OrderServiceImpl class implements the methods from the OrderService interface.

 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

//    @Override
//    public Order findOrderByUser(User user) {
//        return orderRepository.findFirstByUser(user);
//    }
    @Override
    public Order getTotalPrice(Order order) {
        double totalPrice = order.getProducts().stream().mapToDouble(Product::getPrice).sum();
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    @Override
    public boolean deleteOrder(Long id)
    {
        orderRepository.deleteById(id);
        return orderRepository.findById(id).isEmpty();
    }

    @Override
    public void addProduct(Order order, Product product) {
        order.getProducts().add(product);
    }
    @Override
    public OrderDTO saveOrder(OrderCreationDTO dto) {
        Order entity = OrderMapper.toCreationEntity(dto);
        entity = orderRepository.save(entity);
        return OrderMapper.toDto(entity);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO dto) {
        Order order = OrderMapper.toEntity(dto);
       boolean exists = orderRepository.findById(order.getId()).isPresent();
            if (exists)
            {
                order = orderRepository.save(order);
                return OrderMapper.toDto(order);
            }
            return null;
    }

    @Override
    public Order findFirstByOrderNumber(Long orderNumber){
        return orderRepository.findFirstByOrderNumber(orderNumber);
    }

    @Override
    public Order assignProduct(OrderProductDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow();
        Product product = productRepository.findById(dto.getProductId()).orElseThrow() ;
        order.getProducts().add(product);
        orderRepository.save(order);
        return order;
    }


}
