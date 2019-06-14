package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTestSuite {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserDao userDao;

    private Order order1;
    private Order order2;
    private Order order3;
    private Order order4;

    private Product product1 = new Product("kalosze", "gumowe", 25);
    private Product product2 = new Product("sweter", "bawełniany", 45);

    private Users user1 = new Users(1234L,"Magda", "Solety", "magda_solety@gmail.com", "Mazowiecka 13, Wrocław", LocalDate.of(2015, 6, 17));
    private Users user2 = new Users(12345L, "Grzegorz", "Matola", "matolagrzegorz87@icloud.com", "Racławicka 10/15, Kraków", LocalDate.of(2017, 11, 15));

    @Before
    public void before() {
        order1 = new Order(2.0, 2, LocalDate.of(2019, 6, 13));
        order2 = new Order(3.0, 4, LocalDate.of(2019, 6, 13));
        order3 = new Order(1.5, 3, LocalDate.of(2019, 6, 14));
        order4 = new Order(2.6, 6, LocalDate.of(2019, 6, 15));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryTestSuite.class);

    @Test
    public void testOrderDaoSaveAndFindAllAndDeleteById() {
        //Given
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        //When
        List<Order> listOfOrders = orderRepository.findAll();
        //Then
        Assert.assertEquals(4, listOfOrders.size());
        //Clean up
        try {
            orderRepository.deleteById(order1.getOrderId());
            orderRepository.deleteById(order2.getOrderId());
            orderRepository.deleteById(order3.getOrderId());
            orderRepository.deleteById(order4.getOrderId());
        } catch (Exception e) {
            System.out.println("Unable to clean up");
        }
    }

    @Test
    public void testOrderDaoSaveAndFindByIdAndDeleteById() {
        //Given
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        Long order3Id = order3.getOrderId();
        //When
        Optional<Order> findOrder = orderRepository.findById(order3Id);
        //Then
        Assert.assertTrue(findOrder.isPresent());
        //Clean up
        try {
            orderRepository.deleteById(order1.getOrderId());
            orderRepository.deleteById(order2.getOrderId());
            orderRepository.deleteById(order3.getOrderId());
            orderRepository.deleteById(order4.getOrderId());
        } catch (Exception e) {
            System.out.println("Unable to clean up");
        }
    }

    @Test
    public void testOrderDaoManyToManyWithProduct() {
        //Given
    order1.getProducts().add(product1);
    order1.getProducts().add(product2);
    product1.getOrders().add(order1);
    product2.getOrders().add(order1);
    //When
    orderRepository.save(order1);
    Long orderId = order1.getOrderId();
    Long productId1 = product1.getId();
    Long productId2 = product2.getId();
    //Then
    Assert.assertNotEquals(0.0, orderId);
    Assert.assertNotEquals(0.0, productId1);
    Assert.assertNotEquals(0.0, productId2);
    //Clean up
    try {
        orderRepository.deleteById(orderId);
    } catch(Exception e) {
        System.out.println("Unable to clean up");
    }
}

    @Test
    public void testOrderDaoManyToOneWithUsers() {
        //Given
   user1.getOrders().add(order1);
   user1.getOrders().add(order2);
   user2.getOrders().add(order3);
   user2.getOrders().add(order4);

        order1.setUsers(user1);
        order2.setUsers(user1);
        order3.setUsers(user2);
        order4.setUsers(user2);
        //When
        userDao.save(user1);
        userDao.save(user2);
        Long userId1 = user1.getId();
        Long userId2 = user2.getId();
        Long orderId1 = order1.getOrderId();
        Long orderId2 = order2.getOrderId();
        Long orderId3 = order3.getOrderId();
        Long orderId4 = order4.getOrderId();
        //Then
        Assert.assertNotEquals(0.0, userId1);
        Assert.assertNotEquals(0.0, userId2);
        Assert.assertNotEquals(0.0, orderId1);
        Assert.assertNotEquals(0.0, orderId2);
        Assert.assertNotEquals(0.0, orderId3);
        Assert.assertNotEquals(0.0, orderId4);
        //Clean up
        try {
            userDao.deleteById(userId1);
            userDao.deleteById(userId2);
        } catch (Exception e) {
            System.out.println("Unable to clean up");
        }
}
}
