package com.ohgiraffers.jpql.section03.projection;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryName {

    @Column(name="CATEGORY_NAME")
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "CategoryName{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
