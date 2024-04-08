package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.TrooperRepository;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;

import java.util.EnumSet;
import java.util.List;

public class TrooperService {
    private final TrooperRepository trooperRepository;

    public TrooperService(TrooperRepository trooperInMemoryRepository) {
        this.trooperRepository = trooperInMemoryRepository;
    }

    public Trooper create(TroopType type, int trainingTime, int trainingCost, Weapon weapon) throws Exception, InvalidTrooperTypeException {
        Trooper trooper = null;
        if (EnumSet.allOf(TroopType.class).contains(type)) {
            if (type.equals(TroopType.ARCHER)) {
                trooper = new Archer(trainingTime, trainingCost, weapon);
            } else{
                trooper = new Barbarian(trainingTime, trainingCost, weapon);
            }
        } else {
            throw new InvalidTrooperTypeException("Invalid trooper type" + type);
        }

        return trooperRepository.insert(trooper);

    }

    public List<Trooper> getTroopers() {
        return trooperRepository.getTroopers();
    }
}
