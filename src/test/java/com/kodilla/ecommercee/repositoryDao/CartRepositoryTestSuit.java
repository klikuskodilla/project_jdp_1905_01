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
    public void testCartDaoSaveAndFindAll(){
        //Given
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);
        //When
        List<Cart> listOfCarts = cartRepository.findAll();
        //Then
        Assert.assertEquals(3,listOfCarts.size());
        Assert.assertNotEquals(null,cartRepository.findById(cart1.getId()));
        Assert.assertNotEquals(null,cartRepository.findById(cart2.getId()));
        Assert.assertNotEquals(null,cartRepository.findById(cart3.getId()));
        //Clean Up
        try{
            cartRepository.deleteById(cart1.getId());
            cartRepository.deleteById(cart2.getId());
            cartRepository.deleteById(cart3.getId());
        }catch (Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }
    @Transactional
    @Test
    public void testCartDaoSaveAndFindById(){
        //Given
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);
        Long IdCart1 = cart1.getId();
        //When
        Optional<Cart> cartFound = cartRepository.findById(IdCart1);
        //Then
        Assert.assertEquals(cart1.getId(),cartFound.get().getId());
        //Clean Up
        try{
            cartRepository.deleteById(cart1.getId());
            cartRepository.deleteById(cart2.getId());
            cartRepository.deleteById(cart3.getId());
        }catch (Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }
    @Transactional
    @Test
    public void testCartDaoDeleteById(){
        //Given
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);
        Long cart2Id = cart2.getId();
        //When
        cartRepository.deleteById(cart2Id);
        List<Cart> cartList = cartRepository.findAll();
        //Then
        Assert.assertFalse(cartList.contains(cart2));
        //Clean Up
        try{
            cartRepository.deleteById(cart1.getId());
            cartRepository.deleteById(cart3.getId());
        }catch (Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }

    @Transactional
    @Test
    public void testCartOneOnOneWithOrder(){
        //Given
        Order order = new Order();
        cart1.setOrder(order);
        //When
        cartRepository.save(cart1);
        Assert.assertNotEquals(0,cartRepository.findById(cart1.getOrder().getOrderId()));
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
        //When
        //Then
        Assert.assertEquals(2,user1.getCarts().size());
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
