package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repositoryDao.GroupRepository;
import javafx.concurrent.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    GroupRepository groupRepository;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<Group> getGroups() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup")
    public void createGroup(Group group) {
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public Group getGroup(Long groupId) throws GroupNotFoundException {
        return new Group("GroupController");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public Group updateGroup(Group group) {
        return new Group("UpdatedGroupController");
    }
}
