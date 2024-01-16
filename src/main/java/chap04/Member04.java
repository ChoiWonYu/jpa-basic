package chap04;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member04 {

    @Id
    //기본 키 생성을 DB에 위임하는 전략
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    public Member04() {
    }

    public Member04(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
