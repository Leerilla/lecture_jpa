package com.ohgiraffers.association.section02.onetomany;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.MapsId;
import java.io.Serializable;

@Embeddable
public class OrderMenuCompositeKey implements Serializable {

    @Column(name = "ORDER_CODE")
    private int refOrderCode;
    private int menuCode;

    protected OrderMenuCompositeKey() {}

    public OrderMenuCompositeKey(int refOrderCode, int menuCode) {
        this.refOrderCode = refOrderCode;
        this.menuCode = menuCode;
    }


    @Override
    public String toString() {
        return "OrderMenuCompositeKey{" +
                "refOrderCode=" + refOrderCode +
                ", menuCode=" + menuCode +
                '}';
    }
}
