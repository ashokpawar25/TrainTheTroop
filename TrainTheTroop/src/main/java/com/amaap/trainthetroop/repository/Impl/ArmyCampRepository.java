package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryArmyCampRepository;

import java.util.List;

public class ArmyCampRepository implements InMemoryArmyCampRepository {
    InMemoryDatabase inMemoryDatabase;

    public ArmyCampRepository(InMemoryDatabase inMemoryDatabase) {
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
