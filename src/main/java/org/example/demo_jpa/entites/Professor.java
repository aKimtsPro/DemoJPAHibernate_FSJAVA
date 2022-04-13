package org.example.demo_jpa.entites;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @Column(name = "professor_id")
    private int id;
    @Column(name = "professor_name")
    private String name;
    @Column(name = "professor_surname")
    private String firstname;

    @ManyToOne
    private Section section;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
