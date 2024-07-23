package com.ohgiraffers.jpql.section02.parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Section02Menu")
@Table(name="TBL_MENU")
public class Menu {

    @Id
    @Column(name = "MENU_CODE")
    private int menuCode;
    @Column(name = "MENU_NAME")
    private String menuName;
    @Column(name = "MENU_PRICE")
    private int menuPrice;
    @Column(name = "CATEGORY_CODE")
    private int categoryCode;
    @Column(name = "ORDERABLE_STATUS")
    private String orderableStatus;

    protected Menu() {}

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}