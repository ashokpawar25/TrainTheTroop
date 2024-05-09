package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.BarrackRepository;
import com.amaap.trainthetroop.repository.db.InMemoryDatabase;
import jakarta.inject.Inject;

import java.util.Queue;

public class InMemoryBarrackRepository implements BarrackRepository {
    InMemoryDatabase inMemoryDatabase;
    @Inject
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
