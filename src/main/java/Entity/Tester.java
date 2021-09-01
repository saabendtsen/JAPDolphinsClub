/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
        
        Person p1 = new Person("Mikkel",1965);
        Person p2 = new Person("Christian",1965);
        
        Address a1 = new Address("Torv", 1234,"Gaden");
        Address a2 = new Address("Gården",1234,"et hul");

        p1.setAddress(a1);
        p2.setAddress(a2);

        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(250);


        p1.addFees(f1);
        p1.addFees(f3);

        p2.addFees(f2);

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();

        System.out.println("P1: ID " + p1.getP_id() + "P2: ID " + p2.getP_id());

        System.out.println(p1.getName() + " Addresse: " + p1.getAddress().getStreet());
        System.out.println("To vej TEst : " + a1.getPerson().getName());

        System.out.println("Hvem har betalt f2 : " + f2.getPerson().getName());

        System.out.println("Hvad har " + p1.getName() +"betalt: ");
        TypedQuery<Fee> q1 = em.createQuery("SELECT f from Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();

        for (Fee f: fees){
            System.out.println(f.getAmount());
        }
        
    }
    
}
