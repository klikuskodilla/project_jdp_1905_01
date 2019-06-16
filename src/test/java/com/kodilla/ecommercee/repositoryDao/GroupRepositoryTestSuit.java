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

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuit {

    @Autowired
    private GroupRepository groupRepository;

    private Group group1;
    private Group group2;
    private Group group3;
    private Group group4;
    private Product product1;
    private Product product2;

    @Before
    public void before() {
        group1 = new Group("Ubrania");
        group2 = new Group("Dodatki");
        group3 = new Group("Biżuteria");
        group4 = new Group("Obuwie");
        product1 = new Product("kurtka zimowa", "woodoporna", 100);
        product2 = new Product("płaszcz", "damski, wełna", 150);
    }

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
        Assert.assertTrue(groupFound.isPresent() && groupFound.get().getGroupId().equals(group4Id));
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
        Long nullId = 0L;

        //THEN
        Assert.assertNotEquals(nullId, groupId);
        Assert.assertNotEquals(nullId, productId1);
        Assert.assertNotEquals(nullId, productId2);
        //CLEAN-UP
        try {
            groupRepository.deleteById(groupId);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }

}
