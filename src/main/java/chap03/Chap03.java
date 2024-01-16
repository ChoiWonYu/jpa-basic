package chap03;

import fixture.TransactionFixture;
import javax.persistence.EntityManager;

public class Chap03 {

    public static void main(String[] args) {
        TransactionFixture.useEntityManager(Chap03::findMemberInFirstCache);
    }

    public static void readInFirstCache(EntityManager entityManager) {
        Member03 member = new Member03("choi");
        entityManager.persist(member);

        // select 쿼리가 전송되지 않음
        Member03 findMember = entityManager.find(Member03.class, 1L);
    }

    public static void findMemberInFirstCache(EntityManager entityManager) {
        Member03 member = new Member03("choi");
        entityManager.persist(member);
        entityManager.flush();

        // 쿼리가 전송됨
        entityManager.find(Member03.class, 1L);
        // 쿼리가 전송되지 않음
        entityManager.find(Member03.class, 1L);
        // 위 두 객체는 동일성이 보장된다.
    }

    public static void writeMember(EntityManager entityManager) {
        Member03 member1 = new Member03("choi");
        Member03 member2 = new Member03("kim");

        entityManager.persist(member1);
        entityManager.persist(member2);

        // 출력 이후 한 번에 쿼리가 전송됨
        // 쓰기 지연
        System.out.println("=======");
    }

    public static void updateMember(EntityManager entityManager) {

        Member03 member = new Member03("choi");
        entityManager.persist(member);
        entityManager.flush();

        member = entityManager.find(Member03.class, 1L);
        // 별도의 처리 없이 객체가 수정되고, 트랜잭션이 커밋되면 변경 사항이 동기화된다.점
        // 변경 감지
        member.setName("ch");
    }
}
