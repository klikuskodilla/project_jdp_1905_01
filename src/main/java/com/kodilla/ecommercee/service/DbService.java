package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.com.kodilla.ecommercee.domain.Users;
import com.kodilla.ecommercee.repositoryDao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private UserDao userDao;

    public List<Users> getAllCustomer() {
        return userDao.findAll();
    }

    public Users getCustomerById(final Long customerId) {
        return userDao.findById(customerId).orElse(null);
    }

    public Users saveCustomer(final Users customer) {
        return userDao.save(customer);
    }

    public void deleteCustomer(final Long customerId) {
        userDao.deleteById(customerId);
    }
}
