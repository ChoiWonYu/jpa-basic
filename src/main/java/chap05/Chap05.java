package chap05;

import fixture.TransactionFixture;
import java.util.List;
import javax.persistence.EntityManager;

public class Chap05 {

    public static void main(String[] args) {
        TransactionFixture.useEntityManager(Chap05::ownerMistake2);
    }

    public static void joinMemberAndTeam(EntityManager entityManager) {
        Team05 team = new Team05();
        team.setName("TeamA");
        entityManager.persist(team);

        Member05 member = new Member05("member1");
        member.changeTeam(team);
        entityManager.persist(member);

        entityManager.flush();
        entityManager.clear();

        Member05 findMember = entityManager.find(Member05.class, 1L);
        Team05 findTeam = findMember.getTeam();

        findTeam.setName("new team");
    }

    public static void twoWayRelation(EntityManager entityManager) {
        Team05 team = new Team05();
        team.setName("TeamA");
        entityManager.persist(team);

        Member05 member1 = new Member05("member1");
        member1.changeTeam(team);
        entityManager.persist(member1);

        entityManager.flush();
        entityManager.clear();

        Team05 findTeam = entityManager.find(Team05.class, 1L);
        Member05 member2 = new Member05("member2");
        entityManager.persist(member2);
        List<Member05> members = findTeam.getMembers();
        //실제 DB에는 추가되지 않는다.
        members.add(member2);

        System.out.println("===== Member names =====");
        //두 멤버가 출력되지만 이는 DB에 저장된 멤버가 아닌 순수 객체에 참조가 이어져 있는 것이다.
        for (Member05 m : members) {
            System.out.println(m.getName());
        }
    }

    public static void ownerMistake1(EntityManager entityManager) {
        Member05 member = new Member05("member1");
        entityManager.persist(member);

        Team05 team = new Team05();
        team.setName("TeamA");
        //연관관계의 주인이 아니라면 참조를 추가해도 DB에 저장되지 않는다.
        //외래 키가 없으니 Member에 접근할 수 없다.
        team.getMembers().add(member);
        entityManager.persist(team);
    }

    public static void ownerMistake2(EntityManager entityManager) {
        Team05 team = new Team05();
        team.setName("TeamA");
        entityManager.persist(team);

        Member05 member = new Member05("member1");
        member.changeTeam(team);
        entityManager.persist(member);

        entityManager.flush();
        entityManager.clear();

        System.out.println("==========");
        //연관관계의 주인인 멤버에서 team을 지정했지만, 순수 객체에서 team에 멤버가 추가되지 않았다.
        //따라서 멤버가 출력되지 않는다.
        for (Member05 m : team.getMembers()) {
            System.out.println("name = " + m.getName());
        }
        System.out.println("==========");

        Team05 findTeam = entityManager.find(Team05.class, 1L);
        System.out.println("==========");

        //join 쿼리는 아래 getMembers()를 실행했을 때 전송된다.
        for (Member05 m : findTeam.getMembers()) {
            System.out.println("name = " + m.getName());
        }
        System.out.println("==========");

    }

}
