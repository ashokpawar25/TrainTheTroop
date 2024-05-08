package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.entity.Trooper;

import java.util.Queue;

public interface BarrackRepository {
    void addTroopers(Queue<Trooper> trooperList);

    Queue<Trooper> getTroopersFromBarrack();
}
