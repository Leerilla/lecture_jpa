package com.ohgiraffers.association.section02.onetomany;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_ORDER")
public class Order {

    @Id
    @Column(name = "ORDER_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCode;

    @Column(name = "ORDER_DATE")
    private String orderDate;

    @Column(name = "ORDER_TIME")
    private String orderTime;

    @Column(name = "TOTAL_ORDER_PRICE")
    private int totalOrderPrice;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)      //즉시로딩
    @JoinColumn(name = "ORDER_CODE", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<OrderMenu> orderMenus;

    public Order() {}

    public Order(LocalDate orderDate, LocalTime orderTime, int totalOrderPrice, List<OrderMenu> orderMenus) {
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.orderTime = orderTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.totalOrderPrice = totalOrderPrice;
        this.orderMenus = orderMenus;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderList(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode=" + orderCode +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", totalOrderPrice=" + totalOrderPrice +
                ", orderMenus=" + orderMenus +
                '}';
    }
}
