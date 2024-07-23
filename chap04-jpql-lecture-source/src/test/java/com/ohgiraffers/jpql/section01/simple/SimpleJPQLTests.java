package com.ohgiraffers.jpql.section01.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class SimpleJPQLTests {

    /* 수업목표. 간단한 JPQL 구문을 작성할 수 있다. */
    /* 필기.
    *   JPQL(Java Persistence Query Language)
    *   JPQL은 엔터티 객체를 중심으로 개발할 수 있는 객체 지향 쿼리이다.
    *   SQL보다 간결하며 특정 DBMS에 의존하지 않는다.
    *   방언을 통해 해당 DBMS에 맞는 SQL을 실행하게 된다.
    *   JPQL은 find() 메소드를 통한 조회와 다르게 항상 데이터베이스에 SQL을 실행해서 결과를 조회한다.
    * */

    /* 필기.
    *   JPQL의 기본 문법
    *   SELECT, UPDATE, DELETE 등의 키워드 사용은 SQL과 동일하다.
    *   INSERT 는 persist() 메소드를 사용하면 된다.
    *   키워드는 대소문자를 구분하지 않지만, 엔터티와 속성은 대소문자를 구분함에 유의한다.
    *   엔터티 별칭을 필수로 사용해야 하며 별칭 없이 작성하면 에러가 발생한다.
    * */

    /* 필기.
    *   JPQL 사용 방법은 다음과 같다.
    *   1. 작성한 JPQL(문자열)을 `entityManager.createQuery()` 메소드를 통해 쿼리 객체로 만든다.
    *   2. 쿼리 객체는 `TypedQuery`, `Query` 두 가지가 있다.
    *    2-1. TypedQuery : 반환할 타입을 명확하게 지정하는 방식일 때 사용하며 쿼리 객체의 메소드 실행 결과로 지정한 타입이 반환 된다.
    *    2-2. Query : 반환할 타입을 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로 Object 또는 Object[]이 반환 된다.
    *   3. 쿼리 객체에서 제공하는 메소드 `getSingleResult()` 또는 `getResultList()`를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.
    *    3-1. getSingleResult() : 결과가 정확히 한 행일경우 사용하며 없거나 많으면 예외가 발생한다.
    *    3-2. getResultList() : 결과가 2행 이상일 경우 사용하며 컬렉션을 반환한다. 결과가 없으면 빈 컬렉션을 반환한다.
	* */

    @Autowired
    private MenuFindService menuFindService;

    @DisplayName("TypedQuery를 이용한 단일 메뉴 조회 테스트")
    @ParameterizedTest
    @CsvSource({"1,열무김치라떼", "2,우럭스무디", "3,생갈치쉐이크"})
    void testSingleResultWithTypedQuery(int menuCode, String expectedMenuName) {

        //when
        String foundMenuName = menuFindService.findMenuNameByMenuCode(menuCode);

        //then
        Assertions.assertEquals(expectedMenuName, foundMenuName);
    }

    @DisplayName("Query를 이용한 단일 메뉴 조회 테스트")
    @ParameterizedTest
    @CsvSource({"1,열무김치라떼", "2,우럭스무디", "3,생갈치쉐이크"})
    void testSingleResultWithQuery(int menuCode, String expectedMenuName) {

        //when
        Object foundMenuName = menuFindService.findObjectByMenuCode(menuCode);

        //then
        Assertions.assertEquals(expectedMenuName, foundMenuName);
    }

    @DisplayName("TypedQuery를 이용한 다중행 조회 테스트")
    @Test
    void testMultiRowResultWithTypedQuery() {

        //when
        List<Menu> foundMenus = menuFindService.findAllMenusWithTypedQuery();

        //then
        Assertions.assertNotNull(foundMenus);
        foundMenus.forEach(System.out::println);
    }

    @DisplayName("Query를 이용한 다중행 조회 테스트")
    @Test
    void testMultiRowResultWithQuery() {

        //when
        List<Menu> foundMenus = menuFindService.findAllMenusWithQuery();

        //then
        Assertions.assertNotNull(foundMenus);
        foundMenus.forEach(System.out::println);
    }

    /* 설명
    *   JPQL에서 사용할 수 있는 연산자는 SQL과 크게 다르지 않다.
    *   몇 가지만 추가로 더 테스트 해보자.
    *  */

    @DisplayName("DISTINCT를 활용한 중복제거 여러 행 조회 테스트")
    @Test
    void testDistinctKeyword() {

        //when
        List<Integer> categoryCodes = menuFindService.findAllCategoryCodsInMenu();

        //then
        Assertions.assertNotNull(categoryCodes);
        categoryCodes.forEach(System.out::println);
    }

    private static Stream<Arguments> menuCodeList() {
        return Stream.of(
                Arguments.of(List.of(4,5,6,7)),
                Arguments.of(List.of(8,9,10)),
                Arguments.of(List.of(11,12))
        );
    }

    @DisplayName("IN 연산자를 활용한 조회 테스트")
    @ParameterizedTest
    @MethodSource("menuCodeList")
    void testInOperator(List<Integer> categoryCodes) {

        //when
        List<Menu> foundMenus = menuFindService.findMenusInCategoryCodes(categoryCodes);

        //then
        Assertions.assertNotNull(foundMenus);
        foundMenus.forEach(System.out::println);
    }

    @DisplayName("LIKE 연산자를 활용한 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"딸기", "밥", "마늘"})
    void testLikeOperator(String searchValue) {

        //when
        List<Menu> foundMenus = menuFindService.searchMenusBySearchValue(searchValue);

        //then
        Assertions.assertNotNull(foundMenus);
        foundMenus.forEach(System.out::println);
    }
}
