package org.example.demo_jpa.entites.demo_db;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "select_by_name", query = "SELECT e FROM Employe e WHERE e.nom = :nom")
})
public class Employe {

    @Id
    @Column(name = "national_nbr")
    private int id;

    @Column(
            name = "employe_name",
            nullable = false,
            length = 20,
            unique = true
    )
    private String nom;

    @Column(name = "employe_firstname", nullable = false, length = 20)
    private String prenom;

    // Je ne suis pas obligé d'utiliser @Column
    private LocalDate dateNaiss;

    @OneToMany(mappedBy = "employe")
    private List<Travail> travails;

    public Employe() {
    }

    public Employe(String nom, String prenom, LocalDate dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaiss=" + dateNaiss +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return id == employe.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public List<Travail> getTravails() {
        return travails;
    }

    public void setTravails(List<Travail> travails) {
        this.travails = travails;
    }
}
