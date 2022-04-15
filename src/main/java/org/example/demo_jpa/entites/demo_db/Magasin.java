package org.example.demo_jpa.entites.demo_db;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "magasin")
public class Magasin {

    // store_id, auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private long id;

    @Column(name = "store_name", nullable = false, length = 40)
    private String nom;

    @Column(name = "store_address", length = 200)
    private String adresse;

    @ManyToMany
    @JoinTable(
            name = "emploi",
            joinColumns = @JoinColumn(name = "magasin_id"),
            inverseJoinColumns = @JoinColumn(name = "employe_id")
    )
    private List<Employe> employes;

    public Magasin() {
    }

    public Magasin(long id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
