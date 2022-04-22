package org.example.demo_jpa.entites.demo_db;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Customer")
//@IdClass(ClientId.class)
public class Client {

    @EmbeddedId
    private ClientID clientId;

//    @Id
//    private String nom;
//    @Id
//    private String prenom;
    private LocalDate inscription;
    private boolean actif;

    public Client() {
    }

    public Client(String nom, String prenom, LocalDate inscription, boolean actif) {
//        this.nom = nom;
//        this.prenom = prenom;
        this.clientId = new ClientID(nom, prenom);
        this.inscription = inscription;
        this.actif = actif;
    }

//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }

    public LocalDate getInscription() {
        return inscription;
    }

    public void setInscription(LocalDate inscription) {
        this.inscription = inscription;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
