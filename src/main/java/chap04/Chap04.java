package chap04;

import fixture.TransactionFixture;
import javax.persistence.EntityManager;

public class Chap04 {

    public static void main(String[] args) {
        TransactionFixture.useEntityManager(Chap04::identityStrategyQuery);
    }

    public static void identityStrategyQuery(EntityManager entityManager) {
        Member04 member = new Member04("choi");
        entityManager.persist(member);
        //영속성 컨텍스트에 저장하기 위해 persist 하자마자 insert query 전송
        //쓰기 지연이 동작하지 않는다.
        System.out.println("member saved");
    }
}
