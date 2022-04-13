package org.example.demo_jpa;

import org.example.demo_jpa.entites.Course;
import org.example.demo_jpa.entites.Section;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceDemoMySQL");
        EntityManager em = emf.createEntityManager();

        Section s = em.find(Section.class, 1010);
        System.out.println(s.getSectionId() + " - " + s.getSectionName() + " - " + s.getDelegateId());

        Course c = em.find(Course.class, "EG1010");
        System.out.println(c);

        em.close();
        emf.close();

    }

}
