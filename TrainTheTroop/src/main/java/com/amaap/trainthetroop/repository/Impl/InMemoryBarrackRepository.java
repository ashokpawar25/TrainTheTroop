package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.db.InMemoryDatabase;

import java.util.Queue;

public class InMemoryBarrackRepository implements com.amaap.trainthetroop.repository.BarrackRepository {
    InMemoryDatabase inMemoryDatabase;

    public InMemoryBarrackRepository(InMemoryDatabase inMemoryDatabase) {
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
