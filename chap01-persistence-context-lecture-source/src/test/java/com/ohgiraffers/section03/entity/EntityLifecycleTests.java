package com.ohgiraffers.section03.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityLifecycleTests {

    /* 수업목표. 영속성 컨텍스트 내의 엔티티의 생명주기에 대해 이해할 수 있다. */
    /* 필기.
        영속성 컨텍스트(persistence context)
     *  엔티티 메니저가 엔티티 객체를 저장하는 공간으로 엔티티 객체를 보관하고 관리한다.
     *  (엔티티 매니저가 생성될 때 하나의 영속성 컨텍스트가 만들어 진다.)
     * */

    /* 필기.
     *  엔티티의 생명주기
     *  비영속(new/transient): 영속성 컨텍스트와 전혀 관계가 없는 상태 (식별자가 존재하지 않을 수 있다.)
     *  영속(managed): 영속성 컨텍스트에 저장된 상태
     *  준영속(detached): 영속성 컨텍스트에 저장되었다가 분리된 상태 (merge가 가능한 상태)
     *  삭제(removed): 삭제된 상태
     */

    private EntityLifecycle lifecycle;

    @BeforeEach
    void setup() {
        lifecycle = new EntityLifecycle();
    }

    @DisplayName("비영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testTransient(int menuCode) {

        //when
        Menu foundMenu = lifecycle.findMenuByMenuCode(menuCode);

        Menu newMenu = new Menu(
                menuCode,
                foundMenu.getMenuName(),
                foundMenu.getMenuPrice(),
                foundMenu.getCategoryCode(),
                foundMenu.getOrderableStatus()
        );

        //then
        Assertions.assertNotEquals(newMenu, foundMenu);
    }

    @DisplayName("다른 엔티티 매니저가 관리하는 엔티티의 영속성 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testManagedOtherEntityManager(int menuCode) {

        //when
        Menu menu1 = lifecycle.findMenuByMenuCode(menuCode);
        Menu menu2 = lifecycle.findMenuByMenuCode(menuCode);

        //then
        Assertions.assertNotEquals(menu1, menu2);
    }

    @DisplayName("같은 엔티티 매니저가 관리하는 엔티티의 영속성 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testManagedSameEntityManager(int menuCode) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        //when
        Menu menu1 = manager.find(Menu.class, menuCode);
        Menu menu2 = manager.find(Menu.class, menuCode);

        //then
        Assertions.assertEquals(menu1, menu2);
    }

    private static Stream<Arguments> newMenu() {
        return Stream.of(
                Arguments.of("새로운 메뉴", 50000, 4, "Y")
        );
    }

    @DisplayName("엔티티 영속성 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu")
    void testManagedNewEntity(String menuName, int menuPrice, int categoryCode, String orderableStatus) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        //when
        Menu menu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);

        transaction.begin();
        manager.persist(menu);
        manager.flush();

        //then
        Assertions.assertTrue(manager.contains(menu));
        transaction.rollback();
    }

    @DisplayName("엔티티 속성 변경 테스트")
    @ParameterizedTest
    @CsvSource({"1,메론죽", "2,김치딸기죽"})
    void testManagedEntityModify(int menuCode, String menuName) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();
        Menu foundMenu = manager.find(Menu.class, menuCode);

        //when
        transaction.begin();
        foundMenu.setMenuName(menuName);
        manager.flush();

        //then
        Menu expectedMenu = manager.find(Menu.class, menuCode);
        Assertions.assertEquals(expectedMenu.getMenuName(), foundMenu.getMenuName());
        transaction.rollback();
    }

    /* 필기.
     *  영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태가 된다면 준영속 상태라고 한다.
     *  그 중 detach는 특정 엔티티만 준영속 상태로 만든다.
     * */
    @DisplayName("준영속성 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11,1000", "12,1000"})
    void testDetachEntity(int menuCode, int menuPrice) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();
        Menu foundMenu = manager.find(Menu.class, menuCode);

        //when
        transaction.begin();
        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);
        manager.flush();

        //then
        Assertions.assertNotEquals(menuPrice, manager.find(Menu.class, menuCode).getMenuPrice());
        transaction.rollback();
    }

    @DisplayName("준영속성 detach 후 다시 영속화 테스트")
    @ParameterizedTest(name="[{index}] 준영속화 된 {0}번 메뉴를 {1}원으로 변경하고 다시 영속화 되는지 확인")
    @CsvSource({"11,1000", "12,1000"})
    void testDetachAndPersist(int menuCode, int menuPrice) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();
        Menu foundMenu = manager.find(Menu.class, menuCode);

        //when
        transaction.begin();
        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        manager.merge(foundMenu);
        manager.flush();

        //then
        Assertions.assertEquals(menuPrice, manager.find(Menu.class, menuCode).getMenuPrice());
        transaction.rollback();
    }

    @DisplayName("준영속성 clear 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testClearPersistenceContext(int menuCode) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        //when
        Menu foundMenu = manager.find(Menu.class, menuCode);
        manager.clear();

        /* 설명.
        *   clear는 영속화 된 모든 엔티티를 영속성 컨텍스트에서 준영속성 상태로 만든다.
        *   새롭게 find 한 엔티티는 이전 객체의 주소가 아닌 새롭게 생성한 객체의 주소를 가지기 때문에
        *   준영속화 된 엔티티와는 다른 객체로 취급하게 된다.
        *  */
        //then
        Menu expectedMenu = manager.find(Menu.class, menuCode);
        Assertions.assertNotEquals(expectedMenu, foundMenu);
    }

    @DisplayName("준영속성 close 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testClosePersistenceContext(int menuCode) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        //when
        Menu foundMenu = manager.find(Menu.class, menuCode);
        manager.close();

        /* 설명.
         *   close는 영속성 컨텍스트를 종료시킨다.
         *   종료시킨 이후 다시 영속성 컨텍스트를 사용하려 하면 IllegalStateException이 발생하게 된다.
         *  */
        //then
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> manager.find(Menu.class, menuCode)
        );
    }

    /* 필기.
     *   remove : 엔티티를 영속성 컨텍스트 및 데이터 베이스에서 삭제한다.
     *   삭제된 엔티티는 commit을 수행하면 데이터베이스에 영구 반영되고 이후 다시 영속화 될 수 없다.
     *   하지만 commit이 진행되지 않으면 데이터베이스에서 다시 조회하여 merge하는 것은 가능하며
     *   새로운 엔티티를 persist 하는 것도 가능하다 (데이터 삽입됨)
     * */
    @DisplayName("영속성 엔티티 삭제 remove 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testRemoveEntity(int menuCode) {

        //given
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        //when
        transaction.begin();

        Menu foundMenu = manager.find(Menu.class, menuCode);
        manager.remove(foundMenu);

        manager.flush();

        //then
        Assertions.assertFalse(manager.contains(foundMenu));
        transaction.rollback();
    }
}
