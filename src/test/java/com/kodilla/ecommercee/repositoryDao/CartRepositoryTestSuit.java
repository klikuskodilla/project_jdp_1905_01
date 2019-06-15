package com.kodilla.ecommercee.repositoryDao;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CartRepositoryTestSuit {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserDao userDao;

    private Cart cart1 = new Cart();
    private Cart cart2 = new Cart();
    private Cart cart3 = new Cart();


    private Users user1;
    private Users user2;
    private Users user3;

    private Product product1;
    private Product product2;
    private Product product3;

    private List<Product> listOfProducts1 = new ArrayList<>();
    private List<Product> listOfProducts2 = new ArrayList<>();
    private List<Product> listOfProducts3 = new ArrayList<>();

    @Before
    public void before(){
        user1 = new Users(1L,"Czesiek","Wladkowski","xxx@xxx","wroclawksa", LocalDate.of(2000,04,30));
        user2 = new Users(2L,"Wlodek","Ciotkowski","xxx@sss","ulica",LocalDate.of(2005,04,30));
        user3 = new Users(3L,"Rychu","Stanislaw","ccccc@cccc","Zamkowa",LocalDate.of(2019,05,12));

        /*user1 = new Users();
        user2 = new Users();
        user3 = new Users();*/

        product1 = new Product("Glosniki","czarne",23);
        product2 = new Product("Telefon","bezprzewodowy",40);
        product3 = new Product("Pokrowiec","przezroczysty",15);

        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);


        listOfProducts1.add(product1);
        listOfProducts1.add(product2);
        listOfProducts2.add(product3);
        listOfProducts2.add(product1);
        listOfProducts3.add(product2);
        listOfProducts3.add(product3);
    }



    @Test
    public void testCartDaoSaveAndFindAll(){
        //Given
        cart1.setProductsList(listOfProducts1);
        cart1.setUser(user1);

        cart2.setProductsList(listOfProducts2);
        cart2.setUser(user2);

        cart3.setProductsList(listOfProducts3);
        cart3.setUser(user3);



        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);


        System.out.println(cart1.getId());
        //When
        List<Cart> listOfCarts = cartRepository.findAll();
        //Then
        Assert.assertEquals(3,listOfCarts.size());
        //Clean Up
        try{
            cartRepository.deleteById(cart1.getId());
            cartRepository.deleteById(cart2.getId());
            cartRepository.deleteById(cart3.getId());

        }catch (Exception e){
            System.out.println("Clean up process failed");
        }

    }

}
