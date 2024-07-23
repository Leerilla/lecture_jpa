package com.ohgiraffers.section01.entitymanager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    /* 필기.
    *   EntityManagerFactory를 Easer Singleton 방식으로 구현한다.
    *   Factory 생성을 위해 resources/META-INF/persistence.xml 파일을 추가하고 설정 내용을 작성한다.
    *   작성 후 Add JPA 설정 추가할것인지 상단에 노란 창이 뜨면 추가한다.
    *  */
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ohgiraffers");

    private EntityManagerFactoryGenerator() {}

    public static EntityManagerFactory getInstance() {

        return factory;
    }
}
