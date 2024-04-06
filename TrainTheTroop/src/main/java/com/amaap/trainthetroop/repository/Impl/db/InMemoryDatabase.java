package com.amaap.trainthetroop.repository.Impl.db;

import com.amaap.trainthetroop.domain.model.Weapon;

public interface InMemoryDatabase {
    boolean insertIntoTrooperTable(int trainingTime, int trainingCost, Weapon weapon);
}
