package chap07;

import fixture.TransactionFixture;
import javax.persistence.EntityManager;

public class Chap07 {

    public static void main(String[] args) {
        TransactionFixture.useEntityManager(Chap07::createAlbum);
    }

    public static void createAlbum(EntityManager entityManager) {
        Album07 album = new Album07("test book", 1000, "test artist");
        entityManager.persist(album);
    }

}
