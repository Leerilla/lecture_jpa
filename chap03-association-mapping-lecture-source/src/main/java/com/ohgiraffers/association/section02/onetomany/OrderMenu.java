package com.ohgiraffers.association.section02.onetomany;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDER_MENU")
public class OrderMenu {

    @EmbeddedId
    private OrderMenuPk orderMenuPk;

    @Column(name = "ORDER_AMOUNT")
    private int orderAmount;

    public OrderMenu() {}

    public OrderMenu(OrderMenuPk orderMenuPk, int orderAmount) {
        this.orderMenuPk = orderMenuPk;
        this.orderAmount = orderAmount;
    }

    public OrderMenuPk getOrderMenuPk() {
        return orderMenuPk;
    }

    public void setOrderMenuPk(OrderMenuPk orderMenuPk) {
        this.orderMenuPk = orderMenuPk;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "orderMenuPk=" + orderMenuPk +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
