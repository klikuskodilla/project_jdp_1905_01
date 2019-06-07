package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Dto.UsersDto;
import com.kodilla.ecommercee.domain.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public Users maptoUser(final UsersDto usersDto) {
        return new Users(
                usersDto.getId(),
                usersDto.getName(),
                usersDto.getSurname(),
                usersDto.geteMailAddress(),
                usersDto.getUserAddress(),
                usersDto.getAccountStartDate()==null? LocalDate.now(): usersDto.getAccountStartDate());
    }

    public UsersDto maptoUserDto(final Users users) {
        return new UsersDto(
                users.getId(),
                users.getName(),
                users.getSurname(),
                users.geteMailAddress(),
                users.getUserAddress(),
                users.getAccountStartDate());
    }

    public List<UsersDto> mapToUserDtoList(final List<Users> userList) {
        return userList.stream()
                .map(t -> new UsersDto(t.getId(), t.getName(), t.getSurname(),t.geteMailAddress(),t.getUserAddress(), t.getAccountStartDate()))
                .collect(Collectors.toList());
    }
}
