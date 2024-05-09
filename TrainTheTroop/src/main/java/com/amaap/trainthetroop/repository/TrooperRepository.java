package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;

import java.util.List;

public interface TrooperRepository {
    Trooper insert(Trooper trooper) ;

    List<Trooper> getTroopers();

    List<Trooper> getTroopersWithCount(int archerCount,int barbarianCount) throws InsufficientTrooperCountException;
}
