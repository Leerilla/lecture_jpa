package com.ohgiraffers.association.section01.manytoone;

import javax.persistence.*;

@Entity
@Table(name="TBL_MENU")
public class Menu {

    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;

//    @ManyToOne(fetch = FetchType.EAGER)         //기본값
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_CODE")
    private Category category;

    /* 필기.
     *  @JoinColumn은 다대일 연관 관계에서 사용되는 어노테이션이다.
     *  @JoinColumn에서 사용할 수 있는 속성들은 다음과 같다.
     *   - name : 참조하는 테이블의 컬럼명을 지정한다.
     *   - referencedColumnName : 참조되는 테이블의 컬럼명을 지정한다.
     *   - nullable : 참조하는 테이블의 컬럼에 NULL 값을 허용할지 여부를 지정한다.
     *   - unique : 참조하는 테이블의 컬럼에 유일성 제약 조건을 추가할지 여부를 지정한다.
     *   - insertable : 새로운 엔티티가 저장될 때, 이 참조 컬럼이 SQL INSERT 문에 포함될지 여부를 지정한다.
     *   - updatable : 엔티티가 업데이트될 때, 이 참조 컬럼이 SQL UPDATE 문에 포함될지 여부를 지정한다.
     *   - columnDefinition : 이 참조 컬럼에 대한 SQL DDL을 직접 지정한다.
     *   - table : 참조하는 테이블의 이름을 지정한다.
     *   - foreignKey : 참조하는 테이블에 생성될 외래 키에 대한 추가 정보를 지정한다.
     *  @ManyToOne은 다대일 연관 관계에서 사용되는 어노테이션이다.
     *  @ManyToOne에서 사용할 수 있는 속성들은 다음과 같다.
     *   - cascade : 연관된 엔티티에 대한 영속성 전이를 설정한다. (ALL, PERSIST, MERGE, REMOVE, DETACH, REFRESH)
     *   - fetch : 연관된 엔티티를 로딩하는 전략을 설정한다. (EAGER, LAZY)
     *   - optional : 연관된 엔티티가 필수인지 선택적인지를 설정한다. (NULL값 포함 여부)
     * */
    private String orderableStatus;

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
