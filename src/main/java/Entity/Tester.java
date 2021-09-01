/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();

        System.out.println("P1: ID " + p1.getP_id() + "P2: ID " + p2.getP_id());

        System.out.println(p1.getName() + " Addresse: " + p1.getAddress().getStreet());
        System.out.println("To vej TEst : " + a1.getPerson().getName());
        
        
    }
    
}
