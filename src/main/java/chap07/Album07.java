package chap07;

import javax.persistence.Entity;

@Entity
public class Album07 extends Item07 {

    private String artist;

    public Album07() {

    }

    public Album07(final String name, final int price, final String artist) {
        super(name, price);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }
}
