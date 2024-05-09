package com.amaap.trainthetroop.repository.db;

import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;

import java.util.List;
import java.util.Queue;

public interface InMemoryDatabase {
    Trooper insertIntoTrooperTable(Trooper trooper) ;

    List<Trooper> getTroopers();
    List<Trooper> getTroopersWithCount(int archerCount,int barbarianCount) throws InsufficientTrooperCountException;

    void insertIntoBarrack(Queue<Trooper> trooperQueue);

    Queue<Trooper> getTroopersFromBarrack();

    void insertIntoArmyCampTable(Trooper trooper);

    List<Trooper> getTroopersFromArmyCamp();
}
