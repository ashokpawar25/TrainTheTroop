package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.db.InMemoryDatabase;
import jakarta.inject.Inject;

import java.util.List;

public class InMemoryArmyCampRepository implements com.amaap.trainthetroop.repository.ArmyCampRepository {
    InMemoryDatabase inMemoryDatabase;
    @Inject
    public InMemoryArmyCampRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public void add(Trooper trooper) {
        inMemoryDatabase.insertIntoArmyCampTable(trooper);
    }

    @Override
    public List<Trooper> getTrainedTroopers() {
        return inMemoryDatabase.getTroopersFromArmyCamp();
    }
}
