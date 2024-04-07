package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryBarrackRepository;

import java.util.Queue;

public class BarrackRepository implements InMemoryBarrackRepository {
    InMemoryDatabase inMemoryDatabase;

    public BarrackRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public void addTroopers(Queue<Trooper> trooperQueue) {
        inMemoryDatabase.insertIntoBarrack(trooperQueue);
    }

    @Override
    public Queue<Trooper> getTroopersFromBarrack() {
        return inMemoryDatabase.getTroopersFromBarrack();
    }
}
