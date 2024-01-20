package chap05;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member05 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    Team05 team;

    public Member05() {
    }

    public Member05(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Team05 getTeam() {
        return team;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void changeTeam(final Team05 team) {
        this.team = team;
    }
}
