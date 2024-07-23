package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CART")
@IdClass(CompositeKey.class)
public class Cart {

    /* 필기.
    *   IdClass를 사용하는 경우
    *   @Id를 사용할 수 있는 컬럼은 기본키에서 사용 가능한 타입만 사용할 수 있다.
    *   (VO 사용 불가)
    * */
    @Id
    @Column(name = "CART_OWNER")
    private int cartOwner;

    @Id
    @Column(name = "ADDED_BOOK")
    private int addedBook;

    @Column(name = "quantity")
    private int quantity;

    protected Cart() {}

    public Cart(int cartOwner, int addedBook, int quantity) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
        this.quantity = quantity;
    }

    public int getCartOwner() {
        return cartOwner;
    }

    public int getAddedBook() {
        return addedBook;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartOwner=" + cartOwner +
                ", addedBook=" + addedBook +
                ", quantity=" + quantity +
                '}';
    }
}
