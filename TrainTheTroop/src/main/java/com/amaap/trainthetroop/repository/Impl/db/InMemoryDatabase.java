package com.amaap.trainthetroop.repository.Impl.db;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;

public interface InMemoryDatabase {
    Trooper insertIntoTrooperTable(Trooper trooper) throws Exception;
}
