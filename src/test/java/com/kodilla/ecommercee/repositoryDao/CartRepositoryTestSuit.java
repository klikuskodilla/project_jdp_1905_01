package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Cart;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositoryTestSuit {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private  UserDao userDao;

    private static Long zeroLong = 0L;

    private Cart cart1;
    private Cart cart2;
    private Cart cart3;

    private Users user1;

    private Product product1;
    private Product product2;
    private Product product3;

    private List<Product> listOfProducts1;
    private List<Product> listOfProducts2;
    private List<Product> listOfProducts3;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartRepositoryTestSuit.class);

    @Before
    public void beforeTest(){
        product1 = new Product("Glosniki","czarne",23);
        product2 = new Product("Telefon","bezprzewodowy",40);
        product3 = new Product("Pokrowiec","przezroczysty",15);

        listOfProducts1 = new ArrayList<>();
        listOfProducts2 = new ArrayList<>();
        listOfProducts3 = new ArrayList<>();

        cart1 = new Cart();
        cart1.setProductsList(listOfProducts1);
        cart2 = new Cart();
        cart2.setProductsList(listOfProducts2);
        cart3 = new Cart();
        cart3.setProductsList(listOfProducts3);
    }

    @Transactional
    @Test
    public void testCartOneOnOneWithOrder(){
        //Given
        Order order = new Order();
        cart1.setOrder(order);
        //When
        cartRepository.save(cart1);
        Optional<Cart> orderId = cartRepository.findById(cart1.getOrder().getOrderId());
        //Then
        Assert.assertNotEquals(zeroLong,orderId);
        //Clean Up
        try{
            cartRepository.deleteById(cart1.getId());
        }catch (Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }
    @Transactional
    @Test
    public void testCartManyToOneUser(){
        //Given
        user1 = new Users();
        user1.getCarts().add(cart1);
        user1.getCarts().add(cart2);
        cart1.setUsers(user1);
        cart2.setUsers(user1);
        //When
        userDao.save(user1);
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        Optional<Users> userCheckCartList = userDao.findById(user1.getId());
        //Then
        Assert.assertEquals(2,userCheckCartList.get().getCarts().size());
        //Clean up
        try{
            userDao.deleteById(user1.getId());
            cartRepository.deleteById(cart1.getId());
            cartRepository.deleteById(cart2.getId());
        }catch (Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }
    @Test
    public void testCartManyToManyProducts(){
        //Given
        product1.getListOfCarts().add(cart1);
        product1.getListOfCarts().add(cart2);
        product2.getListOfCarts().add(cart1);
        product2.getListOfCarts().add(cart2);
        cart1.getProductsList().add(product1);
        cart1.getProductsList().add(product2);
        cart2.getProductsList().add(product1);
        cart2.getProductsList().add(product2);
        //When
        cartRepository.save(cart1);
        Long product1Id = product1.getId();
        cartRepository.save(cart2);
        Long product2Id = product2.getId();
        //Then
        Assert.assertNotEquals(zeroLong,product1Id);
        Assert.assertNotEquals(zeroLong,product2Id);
        //Clean Up
        try{
            cartRepository.deleteById(cart1.getId());
            cartRepository.deleteById(cart2.getId());
        }catch (Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }
}
