package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.Dto.UsersDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    public List<UsersDto> getUser(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsers/{id}")
    public UsersDto getUser( @PathVariable Long id){
        return new UsersDto(1L, "Tomek", "Zdzich", "Zdzich@op.pl","Zdzich Sztrase", LocalDate.now());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser",  consumes = APPLICATION_JSON_VALUE)
    public void addUser( @RequestBody UsersDto customersDto){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateCustomer")
    public UsersDto updateUser(@RequestBody UsersDto customerDto){
        return new UsersDto(1L, "Tomek", "Zdzich", "Zdzich@op.pl","Zdzich Sztrase", LocalDate.now());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser/{id}")
    public void deleteUser( @PathVariable Long id){
    }

}
