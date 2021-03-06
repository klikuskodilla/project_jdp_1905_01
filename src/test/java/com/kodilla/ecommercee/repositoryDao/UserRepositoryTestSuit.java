package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Users;
import com.kodilla.ecommercee.service.DbUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuit {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTestSuit.class);

    private Users users1;

    private Order order1;
    private Order order2;
    private Order order3;
    private static Long zero =0L;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartRepository cartRepository;

    @Before
    public void before(){
        users1 = new Users("User1","Zdzich1", "Zdzich1@op.pl","Ulica Zdzicha 1", LocalDate.now());
        //users2 = new Users("User2","Zdzich2", "Zdzich2@op.pl","Ulica Zdzicha 2", LocalDate.now());
        //users3 = new Users("User3","Zdzich3", "Zdzich3@op.pl","Ulica Zdzicha 3", LocalDate.now());

        order1 = new Order(123.0, 2,LocalDate.now());
        order2 = new Order(100.0, 3,LocalDate.now());
        order3 = new Order(23.0, 1,LocalDate.now());
    }

    @Test
    public void testSaveOneToManyOrders(){

        //Given
        users1.getOrders().add(order1);
        users1.getOrders().add(order2);
        users1.getOrders().add(order3);

        //When
        userDao.save(users1);

        Long orderId1 = order1.getOrderId();
        Long orderId2 = order2.getOrderId();
        Long orderId3 = order3.getOrderId();

        //Then
        assertNotEquals(zero, orderId1);
        assertNotEquals(zero, orderId2);
        assertNotEquals(zero, orderId3);

        //CleanUp
        try {
            userDao.deleteById(users1.getId());
        } catch (Exception e) {
            LOGGER.error("Failed to process ", e.getMessage(),e);
        }
    }

    @Test
    public void testSaveOneToManyCart(){

        //Given
        Cart cart1 = new Cart(order1, users1);
        Cart cart2 = new Cart(order2, users1);
        Cart cart3 = new Cart(order3, users1);

        users1.getCarts().add(cart1);
        users1.getCarts().add(cart2);
        users1.getCarts().add(cart3);

        //When
        userDao.save(users1);

        Long cartId1 = cart1.getId();
        Long cartId2 = cart2.getId();
        Long cartId3 = cart3.getId();

        //Then
        assertNotEquals(zero, cartId1);
        assertNotEquals(zero, cartId2);
        assertNotEquals(zero, cartId3);

        //CleanUp
        try {
            userDao.deleteById(users1.getId());
            cartRepository.deleteById(cartId1);
            cartRepository.deleteById(cartId2);
            cartRepository.deleteById(cartId3);

        } catch (Exception e) {
            LOGGER.error("Failed to process ", e.getMessage(),e);
        }
    }
}
