package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.Dto.UsersDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private DbUserService dbUserService;

    @Autowired
    private UserMapper customerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public List<UsersDto> getUser(){
        return customerMapper.mapToUserDtoList(dbUserService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsers/{id}")
    public UsersDto getUser(@PathVariable Long id)throws UserNotFoundException{
        return customerMapper.maptoUserDto(dbUserService.getUsersById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser",  consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UsersDto customersDto){
        dbUserService.saveUsers(customerMapper.maptoUser(customersDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateCustomer")
    public UsersDto updateUser(@RequestBody UsersDto customerDto){
        return customerMapper.maptoUserDto(dbUserService.saveUsers(customerMapper.maptoUser(customerDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        dbUserService.deleteUsers(id);
    }

}
