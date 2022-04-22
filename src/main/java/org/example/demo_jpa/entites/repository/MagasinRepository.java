package org.example.demo_jpa.entites.repository;

import org.example.demo_jpa.entites.demo_db.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MagasinRepository {

    // Create Read Update Delete
    private final EntityManager manager;

    public MagasinRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void insert( Magasin toInsert ){ // Create
        manager.getTransaction().begin();
        manager.persist( toInsert );
        manager.getTransaction().commit();
        manager.clear();
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
        manager.merge(toUpdate);
        manager.getTransaction().commit();
//        Magasin magasin = getOne( toUpdate.getId() );
//
//        if( magasin != null ){
//            magasin.setAdresse( toUpdate.getAdresse() );
//            magasin.setGerant( toUpdate.getGerant() );
//            magasin.setNom( toUpdate.getNom() );
//            magasin.setEmployes( toUpdate.getEmployes() );
//            magasin.setProduitsDisponibles( toUpdate.getProduitsDisponibles() );
//
//            manager.getTransaction().commit();
//        }
//        else{
//            manager.getTransaction().rollback();
//            throw new RuntimeException("Element à modifier n'existe pas");
//        }
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

    public void addProduct(long idMagasin, Produit produit){
        manager.getTransaction().begin();
        Magasin m = manager.find(Magasin.class, idMagasin);
        if( m!=null && !m.getProduitsDisponibles().contains(produit) ){
            manager.merge(produit);
            m.getProduitsDisponibles().add(produit);
            manager.getTransaction().commit();
        }
        else {
            manager.getTransaction().rollback();
        }
    }


    public void removeProduct(long id, Produit produit){
        manager.getTransaction().begin();

        Magasin m = manager.find(Magasin.class, id);
        int index = m.getProduitsDisponibles().indexOf(produit);
        if(index > -1)
            m.getProduitsDisponibles().remove(index);

        manager.getTransaction().commit();
    }

    public void addProductOfBrand(long idMagasin, String marque){

        manager.getTransaction().begin();

        TypedQuery<Produit> query = manager
                .createQuery("SELECT p FROM Produit p WHERE p.marque = :toto", Produit.class);
        query.setParameter("toto", marque);
        List<Produit> ofBrand = query.getResultList();

//        TypedQuery<Produit> query = manager
//                .createQuery("SELECT o FROM Produit o", Produit.class);
//        List<Produit> produits = query.getResultList();
//        List<Produit> ofBrand = new ArrayList<>();
//
//        for (Produit produit : produits) {
//            if(produit.getMarque() == marque)
//                ofBrand.add(produit);
//        }

        Magasin m = manager.find(Magasin.class, idMagasin);
        m.getProduitsDisponibles().addAll(ofBrand);

        manager.getTransaction().commit();

    }


    public void engager(long idMagasin, Employe employe){
        manager.getTransaction().begin();
        Magasin m = manager.find(Magasin.class, idMagasin);
        if( m!=null ){
            employe = manager.merge(employe);
            Travail t = new Travail(m, employe);
            t.setDateDebut(LocalDate.now());
            manager.persist(t);
            manager.getTransaction().commit();
        }
        else {
            manager.getTransaction().rollback();
        }
    }

    public void licencier(long idMagasin, Employe employe){
        manager.getTransaction().begin();

        Travail t = manager.find(Travail.class, new TravailID(idMagasin, employe.getId()));
        if(t != null && t.getDateFin() == null)
            t.setDateFin(LocalDate.now());

        manager.getTransaction().commit();
    }

    public List<Employe> getWorkingIn(long idMagasin){
        TypedQuery<Employe> query = manager
                .createQuery("""
                        SELECT t.employe
                        FROM Travail t
                        WHERE
                           t.magasin.id = :id AND t.dateFin IS NULL
                        """, Employe.class);
        query.setParameter("id", idMagasin);
        return query.getResultList();
    }

}
