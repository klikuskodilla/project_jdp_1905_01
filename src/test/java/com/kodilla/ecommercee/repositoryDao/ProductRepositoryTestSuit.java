package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTestSuit {

    @Autowired
    private ProductRepository productRepository;

    private Product product1 = new Product("kurtka zimowa", "woodoporna", 100 );
    private Product product2 = new Product("płaszcz", "damski, wełna", 150);
    private Product product3 = new Product("buty", "sandały skórzane", 100);
    private Product product4 = new Product("rękawiczki", "rozmiar 7.5", 50);

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTestSuit.class);

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
        Assert.assertEquals(product2, productFound);
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

    @Test
    public void testProductDaoManyToOneGroup(){
        //GIVEN
        Group group = new Group("ubrania");
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        product1.setGroup(group);
        product2.setGroup(group);
        //WHEN
        productRepository.save(product1);
        productRepository.save(product2);
        //THEN
        Assert.assertEquals(2, group.getProductList().size());
        //CLEAN-UP
        try{
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
        }catch(Exception e){
            LOGGER.error("Clean-up process failed.", e.getMessage(), e);
        }
    }

    @Test
    public void testProductDao(){

    }

    //GIVEN
    //WHEN
    //THEN
    //CLEAN-UP
}
