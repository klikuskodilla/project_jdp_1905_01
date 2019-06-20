package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTestSuit {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    private Product product1;
    private Product product2;
    /*
    private Product product3;
    private Product product4;
    */

    @Before
    public void before() {
        product1 = new Product("kurtka zimowa", "woodoporna", 100);
        product2 = new Product("płaszcz", "damski, wełna", 150);
        /*
        product3 = new Product("buty", "sandały skórzane", 100);
        product4 = new Product("rękawiczki", "rozmiar 7.5", 50);
        */
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTestSuit.class);

    /*
    @Test
    public void testProductDaoSaveAndFindAll(){
        //GIVEN
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        //WHEN
        List<Product> listOfProducts = productRepository.findAll();
        //THEN
        Assert.assertEquals(4, listOfProducts.size());
        //CLEAN-UP
        try{
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
            productRepository.deleteById(product3.getId());
            productRepository.deleteById(product4.getId());
        }catch(Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }

    @Test
    public void testProductDaoSaveAndFindById(){
        //GIVEN
        productRepository.save(product1);
        productRepository.save(product2);
        Long product2Id= product2.getId();
        //WHEN
        Optional<Product> productFound = productRepository.findById(product2Id);
        //THEN
        Assert.assertTrue(productFound.isPresent() && productFound.get().getId().equals(product2Id));
        //CLEAN-UP
        try{
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
        }catch(Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }

    @Test
    public void testProductDaoDeleteById() {
        //GIVEN
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        Long product2Id= product2.getId();
        List<Product> listOfProducts = productRepository.findAll();
        //WHEN
        productRepository.deleteById(product2Id);
        //THEN
        Assert.assertFalse(listOfProducts.contains(product2));
        //CLEAN-UP
        try{
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product3.getId());
        }catch(Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }
    */

    @Test
    public void testProductDaoManyToOneGroup(){
        //GIVEN
        Group group = new Group("ubrania");
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        product1.setGroup(group);
        product2.setGroup(group);
        //WHEN
        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        //THEN
        Assert.assertEquals(2, group.getProductList().size());
        //CLEAN-UP
        try{
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
            groupRepository.deleteById(group.getGroupId());
        }catch(Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }

    @Test
    public void testProductDaoManyToManyOrder(){
        //GIVEN
        Order order1 = new Order();
        Order order2 = new Order();
        order1.getProducts().add(product1);
        order1.getProducts().add(product2);
        order2.getProducts().add(product1);
        order2.getProducts().add(product2);
        product1.getOrders().add(order1);
        product1.getOrders().add(order2);
        product2.getOrders().add(order1);
        product2.getOrders().add(order2);
        //WHEN
        productRepository.save(product1);
        productRepository.save(product2);
        Long order1Id = order1.getOrderId();
        Long order2Id = order2.getOrderId();
        Long nullId = 0L;
        //THEN
        Assert.assertNotEquals(nullId, order1Id);
        Assert.assertNotEquals(nullId, order2Id);
        //CLEAN-UP
        try{
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
        }catch(Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }

    @Test
    public void testProductDaoManyToManyCart(){
        //GIVEN
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cart1.getProductsList().add(product1);
        cart1.getProductsList().add(product2);
        cart2.getProductsList().add(product1);
        cart2.getProductsList().add(product2);
        product1.getListOfCarts().add(cart1);
        product1.getListOfCarts().add(cart2);
        product2.getListOfCarts().add(cart1);
        product2.getListOfCarts().add(cart2);
        //WHEN
        productRepository.save(product1);
        productRepository.save(product2);
        Long cart1Id = cart1.getId();
        Long cart2Id = cart2.getId();
        Long nullId = 0L;
        //THEN
        Assert.assertNotEquals(nullId, cart1Id);
        Assert.assertNotEquals(nullId, cart2Id);
        //CLEAN-UP
        try{
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
        }catch(Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }

}
