package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.TrooperRepository;
import com.amaap.trainthetroop.repository.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;

import java.util.List;

public class InMemoryTrooperRepository implements TrooperRepository {
    private final InMemoryDatabase inMemoryDatabase;

    public InMemoryTrooperRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Trooper insert(Trooper trooper) {
        return inMemoryDatabase.insertIntoTrooperTable(trooper);
    }

    @Override
    public List<Trooper> getTroopers() {
        return inMemoryDatabase.getTroopers();
    }

    @Override
    public List<Trooper> getTroopersWithCount(int archerCount, int barbarianCount) throws InsufficientTrooperCountException {
        return inMemoryDatabase.getTroopersWithCount(archerCount,barbarianCount);
    }
}
