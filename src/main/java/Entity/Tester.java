/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import facade.PersonFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author SørenBendtsen
 */
public class Tester {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        populate(em);


        List<Person> personList = pf.getAllPersons();
        Person p1 = pf.getPersonById(11);

        System.out.println(pf.noOfSwimStyleForPerson(p1));


        for(Person p : pf.personsToSwimstyle("crawl")){
            System.out.println(p.getName());
        }

        System.out.println("JPQL sum : " + pf.sumOfAllFees());
        int res= 0;
        for(Person p : pf.getAllPersons()){

            System.out.println(p.getName());
            for(Fee f : p.getFees()){
                System.out.println(f.getId());
                res = res + f.getAmount();
            }
        }
        System.out.println("total fees: " + res);


/*
        for (Person p :personList) {
            System.out.println(p.getName());
            for (Fee f:p.getFees()) {
                System.out.println(f.getAmount());

            }

        }

 */







    }

    public static void populate(EntityManager em) {

        Person p3 = new Person("Jens", 1961);
        Person p4 = new Person("Ole", 1979);
        Person p5 = new Person("Bente", 1983);
        Person p6 = new Person("Dennis", 1939);
        Person p7 = new Person("Ida", 1990);
        Person p8 = new Person("Mette", 1999);
        Person p9 = new Person("Kaj", 1993);
        Person p10 = new Person("Finn", 2002);
        Person p11 = new Person("Charlotte", 2003);
        Person p12 = new Person("Karin", 1970);
        Person p13 = new Person("Gitte", 1975);
        Person p14 = new Person("Hans", 1989);

        Address a1 = new Address("Storegade 10", 2323, "Nr. Søby");
        Address a2 = new Address("Bredgade 14", 1212, "København K");
        Address a3 = new Address("Lillegade 1", 2323, "Nr. Søby");
        Address a4 = new Address("Damvej", 1212, "København K");
        Address a5 = new Address("Ndr Frihavnsgade 12", 2100, "Kbh Ø");
        Address a6 = new Address("Østerbrogade 85", 1212, "København K");
        Address a7 = new Address("Nørregade 4", 2200, "Nr. Søby");
        Address a8 = new Address("Nørregade 5", 2200, "København K");
        Address a9 = new Address("Odensegade 64", 2323, "Nr. Søby");
        Address a10 = new Address("Århusgade 29", 2300, "København S");

        p3.setAddress(a1);
        p4.setAddress(a2);
        p5.setAddress(a3);
        p6.setAddress(a4);
        p7.setAddress(a5);
        p8.setAddress(a6);
        p9.setAddress(a7);
        p10.setAddress(a8);
        p11.setAddress(a9);
        p12.setAddress(a10);
        p13.setAddress(a1);
        p14.setAddress(a2);


        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);
        Fee f4 = new Fee(100);
        Fee f5 = new Fee(100);
        Fee f6 = new Fee(100);
        Fee f7 = new Fee(100);


        p3.addFee(f1);
        p3.addFee(f2);
        p4.addFee(f3);
        p5.addFee(f4);
        p6.addFee(f5);
        p7.addFee(f6);
        p8.addFee(f7);
        p9.addFee(f1);
        p10.addFee(f3);
        p11.addFee(f2);
        p12.addFee(f1);
        p13.addFee(f3);
        p14.addFee(f2);


        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Bryst");
        SwimStyle s3 = new SwimStyle("Kapgang");

        p3.addSwimStyle(s1);
        p3.addSwimStyle(s2);
        p3.addSwimStyle(s3);
        p4.addSwimStyle(s1);
        p4.addSwimStyle(s2);
        p5.addSwimStyle(s1);
        p6.addSwimStyle(s3);
        p7.addSwimStyle(s2);


        em.getTransaction().begin();
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.persist(p6);
        em.persist(p7);
        em.persist(p8);
        em.persist(p9);
        em.persist(p10);
        em.persist(p11);
        em.persist(p12);
        em.persist(p13);
        em.persist(p14);
        em.getTransaction().commit();

    }
}

