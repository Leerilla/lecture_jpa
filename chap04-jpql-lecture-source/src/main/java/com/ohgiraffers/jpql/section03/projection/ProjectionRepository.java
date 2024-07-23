package com.ohgiraffers.jpql.section03.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProjectionRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Section03Menu m")
    List<Menu> findAllMenusWithEntityProjection();

    @Query("SELECT c.categoryName FROM Section03Category c")
    List<CategoryName> findAllCategoriesWithEmbeddedTypeProjection();
}
