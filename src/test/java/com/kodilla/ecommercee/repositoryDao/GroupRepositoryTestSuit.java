package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuit {

    @Autowired
    private GroupRepository groupRepository;

    private Group group1 = new Group("Ubrania");
    private Group group2 = new Group("Dodatki");
    private Group group3 = new Group("Biżuteria");
    private Group group4 = new Group("Obuwie");

    private Product product1 = new Product("kurtka zimowa", "woodoporna", 100 );
    private Product product2 = new Product("płaszcz", "damski, wełna", 150);

    @Test
    public void testGroupDaoSaveAndFindAll(){
        //GIVEN
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);
        //WHEN
        List<Group> listOfGroups = groupRepository.findAll();
        //THEN
        Assert.assertEquals(4, listOfGroups.size());
        //CLEAN-UP
        try {
            groupRepository.deleteById(group1.getGroupId());
            groupRepository.deleteById(group2.getGroupId());
            groupRepository.deleteById(group3.getGroupId());
            groupRepository.deleteById(group4.getGroupId());
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }

    @Test
    public void testGroupDaoSaveAndFindById(){
        //GIVEN
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);
        Long group4Id = group4.getGroupId();
        //WHEN
        Optional<Group> groupFound = groupRepository.findById(group4Id);
        //THEN
        Assert.assertTrue(groupFound.isPresent());
        //CLEAN-UP
        try {
            groupRepository.deleteById(group1.getGroupId());
            groupRepository.deleteById(group2.getGroupId());
            groupRepository.deleteById(group3.getGroupId());
            groupRepository.deleteById(group4.getGroupId());
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
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
        Assert.assertNotEquals(0.0, groupId);
        Assert.assertNotEquals(0.0, productId1);
        Assert.assertNotEquals(0.0, productId2);
        //CLEAN-UP
        try {
            groupRepository.deleteById(groupId);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }

}
