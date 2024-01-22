package chap07;

import javax.persistence.Entity;

@Entity
public class Book07 extends Item07 {

    private String author;
    private String isbn;

    public Book07() {

    }

    public Book07(final String name, final int price, final String author, final String isbn) {
        super(name, price);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
}
