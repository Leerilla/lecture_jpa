package com.ohgiraffers.mapping.section02.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {

    @Column(name = "REGULAR_PRICE")
    private int regularPrice;

    @Column(name = "DISCOUNT_RATE")
    private double discountRate;

    @Column(name = "SELL_PRICE")
    private int sellPrice;

    /* 설명.
    *   엔티티는 기본생성자를 반드시 가져야 한다.
    *   공개 범위를 private나 default로 설정할 수 없다.
    *  */
    protected Price() {}

    public Price(int regularPrice, double discountRate) {
        validateNegativePrice(regularPrice);
        validateNegativeDiscountRate(discountRate);
        this.regularPrice = regularPrice;
        this.discountRate = discountRate;
        this.sellPrice = calcSellPrice(regularPrice, discountRate);
    }

    private void validateNegativePrice(int regularPrice) {
        if(regularPrice < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
    }

    private void validateNegativeDiscountRate(double discountRate) {
        if(discountRate < 0) {
            throw new IllegalArgumentException("할인율은 음수일 수 없습니다.");
        }
    }

    private int calcSellPrice(int regularPrice, double discountRate) {

        return (int) (regularPrice - (regularPrice * discountRate));
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public String toString() {
        return "Price{" +
                "regularPrice=" + regularPrice +
                ", discountRate=" + discountRate +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
