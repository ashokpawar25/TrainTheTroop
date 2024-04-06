package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;

public interface InMemoryTrooperRepository {
    boolean insert(int trainingTime, int trainingCost, Weapon weapon);
}
