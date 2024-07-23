package com.ohgiraffers.mapping.section01.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/* 필기.
 *  해당 클래스를 엔티티로 설정하기 위한 어노테이션이다.
 *  프로젝트 내 다른 패키지에도 동일한 엔티티가 존재하는 경우 해당 엔티티를 식별하기 위한 중복되지 않는 name을 지정해 주어야 한다.
 * */
/* 주의사항!
 *  1. 기본 생성자는 필수로 작성해야 한다.
 *  2. final 클래스, enum, interface, inner class에서는 사용할 수 없다.
 *  3. 저장할 필드에 final을 사용하면 안된다.
 * */
@Entity
/* 필기.
 *  매핑될 테이블의 이름을 작성한다.
 *  생략하면 자동으로 클래스 이름을 테이블의 이름으로 사용한다.
 * */
@Table(name="tbl_member")
public class Member {

    /* 필기.
     *  데이터베이스마다 기본 키를 생성하는 방식이 서로 다르므로 이 문제를 해결하기는 쉽지 않다.
     *  JPA는 이런 문제들을 해결하기 위해 3가지 방식의 설정을 제공한다.
     *  1. IDENTITY : 기본 키 생성을 데이터베이스에 위임한다.
     *  2. SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본 키를 할당한다.
     *  3. TABLE : 키 생성 테이블을 사용한다.
     *
     * 설명.
     *  오라클 데이터베이스는 시퀀스를 제공하지만 MYSQL은 시퀀스 대신 자동으로 숫자를 증가시켜주는 AUTO_INCREMENT기능을 제공한다.
     *  AUTO_INCREMENT같은 기능은 IDENTITY 설정으로, SEQUENCE를 이용하는 곳에서는 SEQUENCE 설정으로 기본 키 사용을 매핑할 수 있다.
     *  따라서 SEQUENCE나 IDENTITY전략은 사용하는 데이터베이스에 의존하게 된다.
     *
     * 필기.
     *  @Id 적용이 가능한 자바 타입
     *  1. 자바 기본형
     *  2. 자바 Wrapper 타입
     *  3. String
     *  4. java.util.Date
     *  5. java.sql.Date
     *  6. java.math.BigDecimal
     *  7. java.math.BigInteger
     * */
    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto increment 사용
    private int memberNo;
    @Column(name = "MEMBER_ID", unique = true, nullable = false, columnDefinition = "varchar(10)")
    private String memberId;
    @Column(name = "MEMBER_PWD", nullable = false)
    private String memberPwd;
    @Column(name = "MEMBER_NAME")
    private String memberName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS", length = 500)
    private String address;
    @Column(name = "ENROLL_DATE")
//    @Temporal(TemporalType.TIMESTAMP)    // java.util.Date or java.util.Calendar 타입만 가능하다
    private LocalDateTime enrollDate;

    /* 필기.
     *  Enum타입 매핑 확인
     *  @Entity 클래스 내에 필드 레벨에 @Enumerated 어노테이션 사용이 가능하다.
     *  Enum 클래스를 활용해서 Enum에 속한 값만 DB에 저장을 할 수 있다.
     *  DB에 저장할 경우 크게 문자열과 숫자 두 가지를 저장할 수 있다.
     *  1. EnumType.ORDINAL은 Enum에 정의된 순서대로 0, 1, 2, ...값을 데이터베이스에 저장한다.
     *    장점 : 데이터베이스에 저장되는 데이터의 크기가 작다.
     *    단점 : 이미 저장된 Enum의 순서를 변경할 수 없다.
     *  2. EnumType.STRING은 Enum에 저장된 이름 그대로의 문자열을 데이터베이스에 저장한다.
     *    장점 : 저장된 Enum의 순서가 바뀌거나 Enum이 추가되어도 안전하다.
     *    단점 : 데이터베이스에 저장되는 데이터의 크기가 ORDINAL에 비해 크다.
     * */
    @Column(name = "MEMBER_ROLE")
//    @Enumerated(EnumType.ORDINAL)         // 0, 1로 매핑
    @Enumerated(EnumType.STRING)            // 문자열로 매핑
    private MemberRole memberRole;
    @Column(name = "STATUS", columnDefinition = "char(1) default 'Y'")
    private String status;

    public Member() {}

    public Member(String memberId, String memberPwd, String memberName, String phone, String address, LocalDateTime enrollDate, MemberRole memberRole, String status) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }
}
