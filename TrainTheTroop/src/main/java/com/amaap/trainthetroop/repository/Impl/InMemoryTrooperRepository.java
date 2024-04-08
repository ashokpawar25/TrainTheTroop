package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;

import java.util.List;

public class InMemoryTrooperRepository implements com.amaap.trainthetroop.repository.TrooperRepository {
    private final InMemoryDatabase inMemoryDatabase;

    public InMemoryTrooperRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Trooper insert(Trooper trooper) throws Exception {
        return inMemoryDatabase.insertIntoTrooperTable(trooper);
    }

    @Override
    public List<Trooper> getTroopers() {
        return inMemoryDatabase.getTroopers();
    }
}
