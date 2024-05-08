package com.amaap.trainthetroop.domain.model;

import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;

public class Barbarian extends Trooper {

    public Barbarian(int trainingTime, int trainingCost, Weapon weapon) throws InvalidTrooperDataException {
        super(trainingTime, trainingCost, weapon);

    }
}
