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
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGroupDaoSaveAndFindAll(){
        //GIVEN
        Group group1 = new Group("Ubrania");
        Group group2 = new Group("Dodatki");
        Group group3 = new Group("Biżuteria");
        Group group4 = new Group("Obuwie");
        groupRepository.save(group1);
        Long id1= group1.getGroupId();
        groupRepository.save(group2);
        Long id2= group2.getGroupId();
        groupRepository.save(group3);
        Long id3= group3.getGroupId();
        groupRepository.save(group4);
        Long id4= group4.getGroupId();
        //WHEN
        List<Group> listOfGroups = groupRepository.findAll();
        //THEN
        Assert.assertEquals(4, listOfGroups.size());
        //CLEAN-UP
        try {
            groupRepository.deleteById(id1);
            groupRepository.deleteById(id2);
            groupRepository.deleteById(id3);
            groupRepository.deleteById(id4);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }

    @Test
    public void testGroupDaoSaveAndFindById(){
        //GIVEN
        Group group1 = new Group("Ubrania");
        Group group2 = new Group("Dodatki");
        Group group3 = new Group("Biżuteria");
        Group group4 = new Group("Obuwie");
        groupRepository.save(group1);
        Long id1= group1.getGroupId();
        groupRepository.save(group2);
        Long id2= group2.getGroupId();
        groupRepository.save(group3);
        Long id3= group3.getGroupId();
        groupRepository.save(group4);
        Long id4= group4.getGroupId();
        //WHEN
        Optional<Group> groupFound = groupRepository.findById(id4);
        //THEN
        Assert.assertNotEquals(group4, groupFound);
        //CLEAN-UP
        try {
            groupRepository.deleteById(id1);
            groupRepository.deleteById(id2);
            groupRepository.deleteById(id3);
            groupRepository.deleteById(id4);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }

    @Test
    public void testGroupDaoSaveWithProducts(){
        //GIVEN
        Product product1 = new Product("kurtka zimowa", "woodoporna", 100 );
        Product product2 = new Product("płaszcz", "damski, wełna", 150);
        Group group = new Group("Ubrania");
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        product1.setGroup(group);
        product2.setGroup(group);
        //WHEN
        groupRepository.save(group);
        Long id = group.getGroupId();
        Long nullId = 0L;
        //THEN
        Assert.assertNotEquals(nullId, id);
        //CLEAN-UP
        try {
            groupRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }

    }

    @Test
    public void testGroupDaoOneToManyWithProduct(){
        //GIVEN
        Product product1 = new Product("kurtka zimowa", "woodoporna", 100 );
        Product product2 = new Product("płaszcz", "damski, wełna", 150);
        Group group = new Group("Ubrania");
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        product1.setGroup(group);
        product2.setGroup(group);
        //WHEN
        groupRepository.save(group);
        Long id = group.getGroupId();
        //THEN
        Assert.assertTrue(group.getProductList().size()>1);
        //CLEAN-UP
        try {
            groupRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Clean-up process failed.");
        }
    }

}
