package com.ohgiraffers.association.section01.manytoone;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MenuRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public Menu findMenuByMenuCode(int menuCode) {

        return entityManager.find(Menu.class, menuCode);
    }
}
