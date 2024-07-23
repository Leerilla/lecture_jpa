package com.ohgiraffers.association.section01.manytoone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ManyToOneAssociationTests {

    /* 수업목표. 연관관계 매핑 중 다대일 연관관계 매핑에 대해 이해할 수 있다. */

    /* 필기.
     *   Association Mapping은 Entity 클래스 간의 관계를 매핑하는 것을 의미한다.
     *  이를 통해 객체를 이용해 데이터베이스의 테이블 간의 관계를 매핑할 수 있다.
	 * */

    /* 필기.
     *  다중성에 의한 분류
     *   연관 관계가 있는 객체 관계에서 실제로 연관을 가지는 객체의 수에 따라 분류된다.
     *   - N:1(ManyToOne) 연관 관계
     *   - 1:N(OneToMany) 연관 관계
     *   - 1:1(OneToOne) 연관 관계
     *   - N:N(ManyToMany) 연관 관계
     *  방향에 따른 분류
     *   테이블의 연관 관계는 외래 키를 이용하여 양방향 연관 관계의 특징을 가진다.
     *   참조에 의한 객체의 연관 관계는 단방향이다.
     *   체간의 연관 관계를 양방향으로 만들고 싶을 경우 반대 쪽에서도 필드를 추가해서 참조를 보관하면 된다.
     *   하지만 엄밀하게 이는 양방향 관계가 아니라 단방향 관계 2개로 볼 수 있다.
     *   - 단방향 연관 관계
     *   - 양방향 연관 관계
	 * */

	/* 필기.
	 *  ManyToOne은 다수의 엔티티가 하나의 엔티티를 참조하는 상황에서 사용된다.
	 *  예를 들어 하나의 카테고리가 여러 개의 메뉴를 가질 수 있는 상황에서 메뉴 엔티티가 카테고리 엔티티를 참조하는 것이다.
	 *  이 때 메뉴 엔티티가 Many, 카테고리 엔티티가 One이 된다.
	 * */

	@Autowired
	private MenuFindService menuFindService;

	@DisplayName("메뉴코드로 메뉴 조회하여 메뉴 이름이 일치하는지 테스트")
	@ParameterizedTest
	@CsvSource({"1,열무김치라떼", "2,우럭스무디"})
	void testSelectMenuCompareToMenuName(int menuCode, String menuName) {

		Menu foundMenu = menuFindService.findMenuByMenuCode(menuCode);

		Assertions.assertEquals(menuName, foundMenu.getMenuName());
	}
	@DisplayName("메뉴코드로 메뉴 조회하여 카테고리 이름이 일치하는지 테스트")
	@ParameterizedTest
	@CsvSource({"1,커피", "2,기타"})
	/* 설명.
	*   지연 로딩의 경우 트랜젝션이 종료되면 session이 close 되면서 준영속 상태가 된다.
	*   엔티티 매니저가 관리하지 않는 객체이기 때문에 세션이 종료되었다는 에러가 발생한다.
	*   지연로딩을 이용하는 경우 동일 트랜젝션 내에서 사용해야 한다.
	*  */
	@Transactional
	void testSelectMenuCompareToCategoryName(int menuCode, String categoryName) {

		Menu foundMenu = menuFindService.findMenuByMenuCode(menuCode);

		Assertions.assertEquals(categoryName, foundMenu.getCategory().getCategoryName());
	}

	/* 필기.
	 *  연관관계 매핑을 하는 경우 INSERT, UPDATE, DELETE도 함께 연관관계 설정된 객체와 함께 DML이 수행된다.
	 *  단, 카테고리가 존재하던 값이 아니므로 부모 테이블(TBL_CATEGORY)에 값이 먼저 들어있어야
	 *  그 카테고리를 참조하는 자식 테이블(TBL_MENU)에 데이터를 넣을 수 있다.
	 *  일반적으로는 부모테이블을 통해서 자식테이블에 데이터를 함께 삽입하기 때문에
	 *  문법적으로는 가능하지만 ManyToOne관계에서 여러 테이블에 동시에 데이터를 삽입하는 일은 거의 없다고 볼 수 있다.
	 *  만약 특수한 상황에 꼭 필요한 상황이 있다면 이 때 필요한 것은
	 *  @ManyToOne 어노테이션에 영속성 전이 설정을 해주는 것이다.
	 *  영속성 전이란 특정 엔터티를 영속화할 때 연관된 엔터티도 함께 영속화 한다는 의미이다.
	 *  cascade=CascadeType.PERSIST를 설정하면 MenuAndCategory 엔터티를 영속화 할 때
	 *  Category 엔터티도 함께 영속화 하게 된다.
	 * */
}
