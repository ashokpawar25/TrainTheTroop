package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.Trooper;

import java.util.Queue;

public interface InMemoryBarrackRepository {
    void addTroopers(Queue<Trooper> trooperList);

    Queue<Trooper> getTroopersFromBarrack();
}
