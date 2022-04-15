package org.example.demo_jpa.entites.repository;

import javax.persistence.EntityManager;

public class MagasinRepository {

    private final EntityManager manager;

    public MagasinRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void insert(){
    }
}
