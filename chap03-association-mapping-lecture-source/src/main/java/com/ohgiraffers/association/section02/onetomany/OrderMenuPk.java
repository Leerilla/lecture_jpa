package com.ohgiraffers.association.section02.onetomany;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderMenuPk implements Serializable {

    @Column(name = "ORDER_CODE")
    private int orderCode;
    @Column(name = "MENU_CODE")
    private int menuCode;

    public OrderMenuPk() {
    }

    public OrderMenuPk(int orderCode, int menuCode) {
        this.orderCode = orderCode;
        this.menuCode = menuCode;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String toString() {
        return "OrderMenuPk{" +
                "orderCode=" + orderCode +
                ", menuCode=" + menuCode +
                '}';
    }
}