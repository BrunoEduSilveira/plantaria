package com.plantariadoluis.plantaria.services;

import com.plantariadoluis.plantaria.models.CustomerModel;
import com.plantariadoluis.plantaria.models.OrderModel;
import com.plantariadoluis.plantaria.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderModel save(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    public Page<OrderModel> findAll(Pageable page) {
        return orderRepository.findAll(page);
    }

    public Optional<OrderModel> findById(long id) {
        return orderRepository.findById(id);
    }

    public List<OrderModel> findOrdersByCustomer(long id) {
        return orderRepository.findOrdersByCustomer(id);
    }

    @Transactional
    public void delete(OrderModel orderModel) {
        orderRepository.delete(orderModel);
    }
}
