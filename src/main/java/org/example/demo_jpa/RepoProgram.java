package org.example.demo_jpa;

import org.example.demo_jpa.entites.demo_db.Employe;
import org.example.demo_jpa.entites.demo_db.Magasin;
import org.example.demo_jpa.entites.demo_db.Produit;
import org.example.demo_jpa.entites.demo_db.Travail;
import org.example.demo_jpa.entites.repository.MagasinRepository;
import org.example.demo_jpa.entites.repository.ProduitRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalTime;

public class RepoProgram {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
        EntityManager manager = emf.createEntityManager();


        MagasinRepository magasinRepository = new MagasinRepository(manager);
        ProduitRepository produitRepository = new ProduitRepository(manager);

        Magasin m = new Magasin("Carrefour", "Jambes");
        magasinRepository.insert(m);

        manager.getTransaction().begin();

        Employe e = new Employe("dubois", "luc", LocalDate.now().minusYears(48));
        e.setId(5);
        manager.persist(e);

        manager.getTransaction().commit();

        magasinRepository.engager(m.getId(), e);

        System.out.println("avant licenciement");
        magasinRepository.getWorkingIn(m.getId())
                .forEach(System.out::println);

        magasinRepository.licencier(m.getId(), e);

        System.out.println("après licenciement");
        magasinRepository.getWorkingIn(m.getId())
                .forEach(System.out::println);

        emf.close();
    }
}
