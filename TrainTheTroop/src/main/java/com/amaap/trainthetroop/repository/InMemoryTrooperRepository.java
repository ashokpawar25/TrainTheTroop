package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.service.model.TroopType;

public interface InMemoryTrooperRepository {
    Trooper insert(Trooper trooper) throws Exception;
}
