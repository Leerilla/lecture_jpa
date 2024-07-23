package com.ohgiraffers.association.section02.onetomany;

public class OrderMenuDTO {

    private int menuCode;
    private int orderAmount;

    public OrderMenuDTO(int menuCode, int orderAmount) {
        this.menuCode = menuCode;
        this.orderAmount = orderAmount;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    @Override
    public String toString() {
        return "OrderMenuDTO{" +
                "menuCode=" + menuCode +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
