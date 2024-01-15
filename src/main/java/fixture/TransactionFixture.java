package fixture;

import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TransactionFixture {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    private final static EntityManager em = emf.createEntityManager();
    private final static EntityTransaction tx = em.getTransaction();

    public static void useEntityManager(Consumer<EntityManager> emConsumer) {
        TransactionFixture.useTransaction(tx -> {
            tx.begin();
            emConsumer.accept(em);
            tx.commit();
        });
    }

    public static void useTransaction(Consumer<EntityTransaction> txConsumer) {
        try {
            txConsumer.accept(tx);
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
