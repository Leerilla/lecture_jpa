package com.ohgiraffers.jpql.section02.parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParameterBindingMenuRepository extends JpaRepository<Menu, Integer> {

    /* 필기.
    *   직접 엔티티매니저를 활용하는 것 보다 스프링 데이터 JPA를 이용하는 방식이 더 많이 사용된다.
    *   JpaRepository를 구현한 인터페이스에 @Query 어노테이션을 사용하여 아래와 같은 방식으로 jpql을 작성한다.
    *  */
    @Query("SELECT m FROM Section02Menu m WHERE m.menuCode = :menuCode")
    public Menu bindParameterWithName(@Param("menuCode") int menuCode);

    @Query("SELECT m FROM Section02Menu m WHERE m.menuCode = ?1")
    public Menu bindParameterWithOrder(int menuCode);
}
