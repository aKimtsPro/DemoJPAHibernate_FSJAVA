package org.example.demo_jpa.entites.demo_db;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TravailID implements Serializable {

    private long magasinId;
    private long employeId;

    public TravailID() {
    }

    public TravailID(long magasinId, long employeId) {
        this.magasinId = magasinId;
        this.employeId = employeId;
    }

    public long getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(long magasinId) {
        this.magasinId = magasinId;
    }

    public long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(long employeId) {
        this.employeId = employeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravailID travailID = (TravailID) o;
        return magasinId == travailID.magasinId && employeId == travailID.employeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(magasinId, employeId);
    }
}
