package com.ohgiraffers.jpql.section01.simple;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MenuRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public String findMenuNameByMenuCode(int menuCode) {

        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = :menuCode";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("menuCode", menuCode);

        return query.getSingleResult();
    }

    public Object findObjectByMenuCode(int menuCode) {

        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = :menuCode";
        Query query = entityManager.createQuery(jpql);      //결과 타입을 명시하지 않음
        query.setParameter("menuCode", menuCode);

        return query.getSingleResult();
    }

    public List<Menu> findAllMenusWithTypedQuery() {

        String jpql = "SELECT m FROM Section01Menu as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);       //한 행의 결과 타입을 기술한다.

        return query.getResultList();
    }

    public List<Menu> findAllMenusWithQuery() {

        String jpql = "SELECT m FROM Section01Menu as m";
        Query query = entityManager.createQuery(jpql);

        return query.getResultList();
    }

    public List<Integer> findAllCategoryCodesInMenu() {

        String jpql = "SELECT DISTINCT m.categoryCode FROM Section01Menu m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);

        return query.getResultList();
    }

    public List<Menu> findMenusInCategoryCodes(List<Integer> categoryCodes) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT m FROM Section01Menu m WHERE m.categoryCode in (");
        int categoryCodesSize = categoryCodes.size();
        for(int i = 0; i < categoryCodesSize; i++) {
            jpql.append(categoryCodes.get(i));
            if(i != categoryCodesSize - 1) {
                jpql.append(", ");
            }
        }
        jpql.append(")");

        TypedQuery<Menu> query = entityManager.createQuery(jpql.toString(), Menu.class);

        return query.getResultList();
    }

    public List<Menu> findMenusBySearchValue(String searchValue) {

        String jpql = "SELECT m FROM Section01Menu m WHERE m.menuName LIKE CONCAT('%', :searchValue, '%')";

        return entityManager.createQuery(jpql, Menu.class)
                .setParameter("searchValue", searchValue)
                .getResultList();
    }
}
