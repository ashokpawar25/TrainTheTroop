package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;
import com.amaap.trainthetroop.repository.TrooperRepository;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;

import java.util.EnumSet;
import java.util.List;

public class TrooperService {
    private final TrooperRepository trooperRepository;

    public TrooperService(TrooperRepository trooperInMemoryRepository) {
        this.trooperRepository = trooperInMemoryRepository;
    }

    public Trooper create(TroopType type, int trainingTime, int trainingCost, Weapon weapon) throws InvalidTrooperTypeException, InvalidTrooperDataException {
        Trooper trooper = null;
        if (EnumSet.allOf(TroopType.class).contains(type)) {
            if (type.equals(TroopType.ARCHER)) {
                trooper = new Archer(trainingTime, trainingCost, weapon);
            } else{
                trooper = new Barbarian(trainingTime, trainingCost, weapon);
            }
        } else {
            throw new InvalidTrooperTypeException("Invalid trooper type :" + type);
        }

        return trooperRepository.insert(trooper);

    }

    public List<Trooper> getTroopers() {
        return trooperRepository.getTroopers();
    }

    public List<Trooper> getTroopersWithCount(int archerCount,int barbarianCount) throws InsufficientTrooperCountException {
        return trooperRepository.getTroopersWithCount(archerCount,barbarianCount);
    }
}
