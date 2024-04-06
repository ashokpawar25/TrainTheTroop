package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryTrooperRepository;

public class TrooperRepository implements InMemoryTrooperRepository {
    private final InMemoryDatabase inMemoryDatabase;

    public TrooperRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public boolean insert(int trainingTime, int trainingCost, Weapon weapon) {
        return inMemoryDatabase.insertIntoTrooperTable(trainingTime,trainingCost,weapon);
    }
}
