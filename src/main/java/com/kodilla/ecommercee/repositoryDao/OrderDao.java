package com.kodilla.ecommercee.repositoryDao;

import com.kodilla.ecommercee.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();

    @Override
    Order save(Order order);

    @Override
    Optional<Order> findById(Long id);

    @Override
    void deleteById(Long id);

}
