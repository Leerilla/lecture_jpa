package com.ohgiraffers.mapping.section01.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class EntityMappingTests {

    @Autowired
    private MemberRegistService memberRegistService;

    /* 수업목표. 엔티티와 테이블을 매핑하는 어노테이션을 사용할 수 있다. */
    /* 사전조건.
     *  스프링 이니셜라이져를 이용하여 boot앱을 만들고 테스트한다.
     *  이 때 dependency는 jpa, mysql, junit params를 추가한다.
     *  */
    /* 필기.
     *  엔티티와 테이블을 정확하게 매핑하는 것이 JPA의 핵심이다.
     *  이를 위해 다양한 매핑 어노테이션(mapping annotation)이 지원되는데 크게 4가지로 분류할 수 있다.
     *  객체와 테이블 : @Entity, @Table
     *  기본키 매핑 : @Id
     *  필드와 컬럼 매핑 : @Column
     *  연관관계 매핑 : @ManyToOne, @OneToMany, @JoinColumn
     * */

    /* 설명.
     *  application.yml 파일에 database 접속 설정과 jpa 설정을 해준다.
     *  이 때 spring.jpa.hibernate.ddl-auto: create 설정을 해주면 ddl 구문이 자동 생성된다.
     *  persistence.xml 파일에 <property name="hibernate.hbm2ddl.auto" value="create"/> 설정과 동일하다.
     *  */

    private static Stream<Arguments> createMember() {
        return Stream.of(
                Arguments.of(
                        1,
                        "user01",
                        "pass01",
                        "홍길동",
                        "010-1234-5678",
                        "서울시 서초구",
                        LocalDateTime.now(),
                        "ROLE_MEMBER",
                        "Y"
                ),
                Arguments.of(
                        2,
                        "user02",
                        "pass02",
                        "유관순",
                        "010-1234-3131",
                        "서울시 서초구",
                        LocalDateTime.now(),
                        "ROLE_ADMIN",
                        "Y"
                )
        );
    }
    @DisplayName("테이블 만들기 테스트")
    @ParameterizedTest
    @MethodSource("createMember")
    void testCreateTable(int memberNo, String memberId, String memberPwd, String memberName,
                         String phone, String address, LocalDateTime enrollDate, MemberRole memberRole, String status) {

        MemberRegistDTO memberInfo = new MemberRegistDTO(
                memberId,
                memberPwd,
                memberName,
                phone,
                address,
                enrollDate,
                memberRole,
                status
        );

        Assertions.assertDoesNotThrow(
                () -> memberRegistService.registMember(memberInfo)
        );
    }
}
