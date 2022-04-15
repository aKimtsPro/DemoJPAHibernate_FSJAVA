package org.example.demo_jpa.entites.repository;

import org.example.demo_jpa.entites.demo_db.Magasin;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MagasinRepository {

    private final EntityManager manager;

    public MagasinRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void insert( Magasin toInsert ){ // Create
        manager.getTransaction().begin();
        manager.persist( toInsert );
        manager.getTransaction().commit();
    }

    public Magasin getOne( long id ){ // Read: recupérer 1 magasin
        return manager.find(Magasin.class, id);
    }

    public List<Magasin> getAll(){ // Read

        TypedQuery<Magasin> query = manager.createQuery(
                "SELECT m FROM Magasin m",
                Magasin.class
        );
        List<Magasin> magasins = query.getResultList();
        return magasins;



    }

    public void update( Magasin toUpdate ){ // Update
        manager.getTransaction().begin();

        Magasin magasin = getOne( toUpdate.getId() );

        if( magasin != null ){
            magasin.setAdresse( toUpdate.getAdresse() );
            magasin.setGerant( toUpdate.getGerant() );
            magasin.setNom( toUpdate.getNom() );
            magasin.setEmployes( toUpdate.getEmployes() );
            magasin.setProduitsDisponibles( toUpdate.getProduitsDisponibles() );

            manager.getTransaction().commit();
        }
        else{
            manager.getTransaction().rollback();
            throw new RuntimeException("Element à modifier n'existe pas");
        }
    }


    // pour supprimer il faut mettre une entité managed dans le status removed
    public void delete( long id ){ // Delete
        manager.getTransaction().begin();

        Magasin toDelete = manager.find(Magasin.class, id);

        if( toDelete != null ){
            manager.remove(toDelete);
            manager.getTransaction().commit();
        }
        else {
            manager.getTransaction().rollback();
            throw new RuntimeException("Element à supprimer n'existe pas");
        }

    }
    // CRUD
}
