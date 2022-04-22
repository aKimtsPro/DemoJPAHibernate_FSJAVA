package org.example.demo_jpa.entites.demo_db;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ClientID implements Serializable {

    private String nom;
    private String prenom;

    public ClientID() {
    }

    public ClientID(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
