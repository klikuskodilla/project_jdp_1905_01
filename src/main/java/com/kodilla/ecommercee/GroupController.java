package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup")
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) throws GroupNotFoundException {
        return new GroupDto("GroupController");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto("UpdatedGroupController");
    }
}
