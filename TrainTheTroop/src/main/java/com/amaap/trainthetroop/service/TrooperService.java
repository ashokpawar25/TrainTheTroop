package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.InMemoryTrooperRepository;
import com.amaap.trainthetroop.service.model.TroopType;

public class TrooperService {
    private InMemoryTrooperRepository inMemoryTrooperRepositery;

    public TrooperService(InMemoryTrooperRepository trooperInMemoryRepository) {
        this.inMemoryTrooperRepositery = trooperInMemoryRepository;
    }

    public boolean create(TroopType type, int trainingTime, int trainingCost, Weapon weapon)
    {
        return inMemoryTrooperRepositery.insert(trainingTime,trainingCost,weapon);

    }
}
