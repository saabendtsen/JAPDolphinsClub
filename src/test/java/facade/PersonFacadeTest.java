package facade;

import Entity.Fee;
import Entity.Person;
import Entity.SwimStyle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_test");

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Fee.deleteAllRows").executeUpdate();
            em.createNamedQuery("SwimStyle.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            Person p1 = new Person("Henning", 1965);
            Person p2 = new Person("Henrietta", 2001);
            p1.addFee(new Fee(55));
            p2.addFee(new Fee(45));
            p1.addSwimStyle(new SwimStyle("Crawl"));
            p1.addSwimStyle(new SwimStyle("Butterfly"));
            p2.addSwimStyle(new SwimStyle("Crawl"));
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAllPersons() {
    }
}