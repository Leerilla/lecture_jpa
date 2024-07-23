package com.ohgiraffers.association.section02.onetomany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class OneToManyAssociationTests {

    @Autowired
    private OrderRegistService orderRegistService;

    @Autowired
    private OrderFindService orderFindService;

    private static Stream<Arguments> orderInfo() {

        return Stream.of(
                Arguments.of(
                    List.of(
                            new OrderMenuDTO(1, 10),
                            new OrderMenuDTO(2, 10)
                    )
                )
        );
    }

    /* 설명.
    *   order 테이블에 insert 후 order_menu 테이블도 insert를 진행한다.
    *   이 때 기본값으로 order_menu의 order_code가 insert된 후
    *   order 테이블에 insert된 order_code를 이용해 order_menu 테이블에 order_code 컬럼을 업데이트 한다.
    *   따라서 초기 insert가 가능하기 위해서는 order_menu 테이블의 fk 제약조건을 해제해주어야 insert가 가능하다.
    * */
    @DisplayName("일대다 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("orderInfo")
    void testInsertOneToManyAssociatedInstance(List<OrderMenuDTO> orderInfo) {

        //when & then
        Assertions.assertDoesNotThrow(
                () -> orderRegistService.registOrder(orderInfo)
        );
    }

    /* 설명.
    *   지연로딩을 하려면 transactional로 트랜젝션을 열어줘야 한다.
    *   먼저 조회하는 엔티티가 영속성 컨텍스트에 존재해야 지연로딩을 할 수 있다.
    *   만약 transactional을 사용하지 않으려면 지연로딩이 아닌 방식(즉시로딩)을 이용해야 한다.
    *  */
    @DisplayName("일대다 연관관계 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {32, 33})       //등록된 주문번호로 테스트한다.
//    @Transactional        //즉시로딩으로 하면 제외해도 됨
    void testFindOrderByOrderCode(int orderCode) {

        Assertions.assertDoesNotThrow(
                () -> {
                    List<Order> orders = orderFindService.findOrderByOrderCode(orderCode);
                    orders.forEach(System.out::println);        //이 부분에서 지연로딩 발생함
                }
        );
    }
}