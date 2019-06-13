package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Users;
import com.kodilla.ecommercee.repositoryDao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbUserService {

    @Autowired
    private UserDao userDao;

    public List<Users> getAllUsers() {
        return userDao.findAll();
    }

    public Users getUsersById(final Long customerId) {
        return userDao.findById(customerId).orElse(null);
    }

    public Users saveUsers(final Users customer) {
        return userDao.save(customer);
    }

    public void deleteUsers(final Long customerId) {
        userDao.deleteById(customerId);
    }
}
