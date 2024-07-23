package com.ohgiraffers.association.section02.onetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderFindService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderFindService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


//    @Transactional
    public List<Order> findOrderByOrderCode(int orderCode) {

        return orderRepository.findOrderByOrderCode(orderCode);
    }
}
