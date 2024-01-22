package chap07;

import javax.persistence.Entity;

@Entity
public class Movie07 extends Item07 {

    private String director;
    private String actor;

    public Movie07() {

    }

    public Movie07(final String name, final int price, final String director, final String actor) {
        super(name, price);
        this.director = director;
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }
}
