package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.impl.exception.InsufficientTrooperCountException;

import java.util.List;

public interface TrooperRepository {
    Trooper insert(Trooper trooper) throws Exception;

    List<Trooper> getTroopers();

    List<Trooper> getTroopersWithCount(int archerCount,int barbarianCount) throws InsufficientTrooperCountException;
}
