package com.amaap.trainthetroop.repository.Impl.db;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.impl.exception.InsufficientTrooperCountException;

import java.util.List;
import java.util.Queue;

public interface InMemoryDatabase {
    Trooper insertIntoTrooperTable(Trooper trooper) throws Exception;

    List<Trooper> getTroopers();
    List<Trooper> getTroopersWithCount(int archerCount,int barbarianCount) throws InsufficientTrooperCountException;

    void insertIntoBarrack(Queue<Trooper> trooperQueue);

    Queue<Trooper> getTroopersFromBarrack();

    void insertIntoArmyCampTable(Trooper trooper);

    List<Trooper> getTroopersFromArmyCamp();
}
