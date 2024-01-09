package chap03;

import fixture.TransactionFixture;
import javax.persistence.EntityManager;

public class FirstCache {

    public static void main(String[] args) {
        TransactionFixture.useEntityManager(FirstCache::readInFirstCache);
    }

    public static void readInFirstCache(EntityManager entityManager) {
        Member member = new Member(4L, "choi");
        entityManager.persist(member);

        // select 쿼리가 전송되지 않음
        Member findMember = entityManager.find(Member.class, 4L);
    }

    public static void findMemberInFirstCache(EntityManager entityManager) {
        // 쿼리가 전송됨
        entityManager.find(Member.class, 1L);
        // 쿼리가 전송되지 않음
        entityManager.find(Member.class, 1L);

        // 위 두 객체는 동일성이 보장된다.
    }

    public static void writeMember(EntityManager entityManager) {
        Member member1 = new Member(1L, "choi");
        Member member2 = new Member(3L, "kim");

        entityManager.persist(member1);
        entityManager.persist(member2);

        // 출력 이후 한 번에 쿼리가 전송됨
        System.out.println("=======");
    }

    public static void updateMember(EntityManager entityManager) {
        Member member = entityManager.find(Member.class, 1L);
        // 별도의 처리 없이 객체가 수정되고, 트랜잭션이 커밋되면 변경 사항이 동기화된다.
        member.setName("ch");
    }
}
