package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Group;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest()
public class GroupRepositoryTestSuit {

    @Autowired
    private GroupRepository groupRepository;

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
            System.out.println("Clean-up process filed.");
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
        Optional<Group> groupFound = groupRepository.findById(id1);
        //THEN
        Assert.assertNotEquals(group1, groupFound);
        try {
            groupRepository.deleteById(id1);
            groupRepository.deleteById(id2);
            groupRepository.deleteById(id3);
            groupRepository.deleteById(id4);
        }catch(Exception e){
            System.out.println("Clean-up process filed.");
        }
    }

}
