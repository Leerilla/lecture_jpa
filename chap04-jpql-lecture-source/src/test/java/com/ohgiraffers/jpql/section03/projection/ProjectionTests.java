package com.ohgiraffers.jpql.section03.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Persistence;
import java.util.List;

@SpringBootTest
public class ProjectionTests {

    /* 수업목표. 다양한 프로젝션에 대해 이해하고 사용할 수 있다. */
    /* 필기.
     *  프로젝션(projection)
     *  SELECT 절에 조회할 대상을 지정하는 것을 프로젝션이라고 한다.
     *  (SELECT {프로젝션 대상} FROM)
     *  프로젝션 대상은 4가지 방식이 있다.
     *  1. 엔티티 프로젝션
     *     원하는 객체를 바로 조회할 수 있다.
     *     조회된 엔티티는 영속성 컨텍스트가 관리한다.
     *  2. 임베디드 타입 프로젝션
     *     엔티티와 거의 비슷하게 사용되며 조회의 시작점이 될 수 없다. -> from 절에 사용 불가
     *     임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다.
     *  3. 스칼라 타입 프로젝션
     *     숫자, 문자, 날짜 같은 기본 데이터 타입이다.
     *     스칼라 타입은 영속성 컨텍스트에서 관리되지 않는다.
     *  4. new 명령어를 활용한 프로젝션
     *     다양한 종류의 단순 값들을 DTO로 바로 조회하는 방식으로 new 패키지명.DTO명을 쓰면 해당 DTO로 바로 반환받을 수 있다.
     *     new 명령어를 사용한 클래스의 객체는 엔티티가 아니므로 영속성 컨텍스트에서 관리되지 않는다.
     * */


    @Autowired
    private ProjectionService projectionService;

    @DisplayName("엔티티 프로젝션 조회 테스트")
    @Test
    void testEntityProjection() {

        //when
        List<Menu> menus = projectionService.findAllMenusWithEntityProjection();

        //then
        Assertions.assertNotNull(menus);
        menus.forEach(System.out::println);
    }

    @DisplayName("임베디드 타입 프로젝션 테스트")
    @Test
    void testEmbeddedTypeProjection() {

        //when
        List<CategoryName> categoryNames = projectionService.findAllCategoriesWithEmbeddedTypeProjection();

        //then
        Assertions.assertNotNull(categoryNames);
        categoryNames.forEach(System.out::println);
    }

    @DisplayName("스칼라 타입 프로젝션 테스트")
    @Test
    void testScalarTypeProjection() {

    }

    @DisplayName("new 명령어를 활용한 프로젝션 테스트")
    @Test
    void testNewKeywordProjection() {

    }

}
