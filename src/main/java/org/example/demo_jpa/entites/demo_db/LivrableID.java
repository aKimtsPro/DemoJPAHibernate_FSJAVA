package org.example.demo_jpa.entites.demo_db;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LivrableID implements Serializable {

    private long idFournisseur;
    private long idProduit;

}
