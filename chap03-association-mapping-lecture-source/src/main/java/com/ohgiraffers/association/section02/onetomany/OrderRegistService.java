package com.ohgiraffers.association.section02.onetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderRegistService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderRegistService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void registOrder(List<OrderMenuDTO> orderInfo) {

        List<OrderMenu> orderMenus = orderInfo.stream()
                .map(orderDetail -> new OrderMenu(new OrderMenuPk(0, orderDetail.getMenuCode()), orderDetail.getOrderAmount()))
                .collect(Collectors.toList());

        Order order = new Order(
                LocalDate.now(),
                LocalTime.now(),
                95000,          //가격은 편의상 계산된 가격을 넣는다.
                orderMenus
        );

        System.out.println("order = " + order);

        orderRepository.save(order);
    }
}
