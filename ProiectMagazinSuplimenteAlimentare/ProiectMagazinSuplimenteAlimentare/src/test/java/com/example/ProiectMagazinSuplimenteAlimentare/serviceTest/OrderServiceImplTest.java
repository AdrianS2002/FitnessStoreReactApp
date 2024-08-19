//package com.example.ProiectMagazinSuplimenteAlimentare.serviceTest;
//
//import com.example.ProiectMagazinSuplimenteAlimentare.model.Order;
//import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
//import com.example.ProiectMagazinSuplimenteAlimentare.repository.OrderRepository;
//import com.example.ProiectMagazinSuplimenteAlimentare.service.order.OrderServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class OrderServiceImplTest {
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    private OrderServiceImpl orderService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        orderService = new OrderServiceImpl(orderRepository);
//    }
//
//    @Test
//    public void testSaveOrder() {
//        Order order = new Order();
//        when(orderRepository.save(any(Order.class))).thenReturn(order);
//
//        orderService.saveOrder(order);
//
//        verify(orderRepository, times(1)).save(order);
//    }
//
//    @Test
//    public void testDeleteOrder() {
//        Long id = 1L;
//        doNothing().when(orderRepository).deleteById(id);
//
//        orderService.deleteOrder(id);
//
//        verify(orderRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    public void testGetTotalPrice() {
//        Product product1 = new Product();
//        product1.setPrice(100.0);
//        Product product2 = new Product();
//        product2.setPrice(200.0);
//
//        Order order = new Order();
//        //   order.addProduct(product1);
//        //order.addProduct(product2);
//
//        double expectedTotal = 300.0;
//        double actualTotal = order.getTotalPrice();
//
//        assertEquals(expectedTotal, actualTotal, "The total price should be the sum of the prices of the products in the order");
//    }
//}
