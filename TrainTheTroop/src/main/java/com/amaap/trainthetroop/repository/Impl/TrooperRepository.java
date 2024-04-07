package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryTrooperRepository;
import com.amaap.trainthetroop.service.model.TroopType;

public class TrooperRepository implements InMemoryTrooperRepository {
    private final InMemoryDatabase inMemoryDatabase;

    public TrooperRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Trooper insert(Trooper trooper) throws Exception {
        return inMemoryDatabase.insertIntoTrooperTable(trooper);
    }
}
