package facade;

import Entity.Person;

import javax.persistence.*;
import java.util.List;

public class PersonFacade {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static PersonFacade instance;

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        PersonFacade pf = getPersonFacade(emf);
        Person p1 = new Person("krisser", 1300);

        pf.createPerson(p1);

        System.out.println(pf.getPersonById(1).getName());
        p1.setName("Philip");
        System.out.println(pf.updatePerson(p1));
        System.out.println(p1.getName());
        pf.deletePerson(1);




    }


    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> persons = query.getResultList();
            return persons;
        } finally {
            em.close();
        }
    }

    public Person getPersonById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.p_id = :id", Person.class);
            query.setParameter("id", id);
            Person person = query.getSingleResult();
            return person;
        } finally {
            em.close();
        }
    }

    public void createPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }


    public boolean updatePerson(Person p) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();

        return false;
    }

    public void deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person p = getPersonById(id);

        em.getTransaction().begin();
        if (!em.contains(p)) {
            p = em.merge(p);
        }
        em.remove(p);
        em.getTransaction().commit();

    }

    public List<Person> allPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return persons;

    }

    public Long noOfSwimStyleForPerson(Person p) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT COUNT(s) FROM Person p JOIN p.styles s WHERE p.p_id =:p_id ");
        query.setParameter("p_id", p.getP_id());
        Long res = (Long) query.getSingleResult();
        return res;
    }

    public List<Person> personsToSwimstyle(String style) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.styles s WHERE s.styleName =:style ",Person.class);
        query.setParameter("style",style);
        List<Person> personList = query.getResultList();
        return personList;
    }

    public Long sumOfAllFees(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT SUM(f.amount) FROM Fee f ");
        Long res = (Long) query.getSingleResult();
        return res;
    }




}


