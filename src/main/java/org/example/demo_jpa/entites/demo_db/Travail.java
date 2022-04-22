package org.example.demo_jpa.entites.demo_db;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Travail {

    @EmbeddedId
    private TravailID id = new TravailID();

    @MapsId("magasinId")
    @ManyToOne
    @JoinColumn(name = "magasin_id")
    private Magasin magasin;

    @MapsId("employeId")
    @ManyToOne
    @JoinColumn(name = "employe_id", columnDefinition = "INT")
    private Employe employe;

    @Column(nullable = false)
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Travail() {
    }

    public Travail(Magasin magasin, Employe employe) {
        this.magasin = magasin;
        this.employe = employe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travail travail = (Travail) o;
        return id.equals(travail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public TravailID getId() {
        return id;
    }

    public void setId(TravailID id) {
        this.id = id;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
