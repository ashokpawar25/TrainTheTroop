package com.amaap.trainthetroop.repository.Impl.db;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;

import java.util.List;
import java.util.Queue;

public interface InMemoryDatabase {
    Trooper insertIntoTrooperTable(Trooper trooper) throws Exception;

    List<Trooper> getTroopers();

    void insertIntoBarrack(Queue<Trooper> trooperQueue);
}
