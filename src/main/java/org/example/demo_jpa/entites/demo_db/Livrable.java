package org.example.demo_jpa.entites.demo_db;

import javax.persistence.*;

@Entity
public class Livrable {

    @EmbeddedId
    private LivrableID id;

    @MapsId("idFournisseur")
    @ManyToOne
    private Fournisseur fournisseur;

    @MapsId("idProduit")
    @ManyToOne
    private Produit produit;

    private int lotMin;
    private int lotMax;

}
