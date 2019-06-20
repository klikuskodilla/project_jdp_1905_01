package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuit {

    @Autowired
    private GroupRepository groupRepository;

    private Group group1;
    private Product product1;
    private Product product2;
    private static Long zeroLong = 0L;

    @Before
    public void before() {
        group1 = new Group("Ubrania");
        product1 = new Product("kurtka zimowa", "woodoporna", 100);
        product2 = new Product("płaszcz", "damski, wełna", 150);
    }

    @Test
    public void testGroupDaoOneToManyWithProduct(){
        //GIVEN
        group1.getProductList().add(product1);
        group1.getProductList().add(product2);
        product1.setGroup(group1);
        product2.setGroup(group1);
        //WHEN
        groupRepository.save(group1);
        Long groupId = group1.getGroupId();
        Long productId1 = product1.getId();
        Long productId2 = product2.getId();
        //THEN
        Assert.assertNotEquals(zeroLong, groupId);
        Assert.assertNotEquals(zeroLong, productId1);
        Assert.assertNotEquals(zeroLong, productId2);
        //CLEAN-UP
        try {
            groupRepository.deleteById(groupId);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }

}
