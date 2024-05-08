package com.amaap.trainthetroop.domain.model.entity;

import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrainingCostException;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrainingTimeException;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidWeaponException;
import com.amaap.trainthetroop.domain.model.entity.validator.TrooperDataValidator;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;

import static com.amaap.trainthetroop.domain.model.entity.validator.TrooperDataValidator.*;

public class Trooper {
    private final int trainingTime;
    private final int trainingCost;
    private final Weapon weapon;

    public Trooper(int trainingTime, int trainingCost, Weapon weapon) throws InvalidTrooperDataException {
        if (isInvalidTime(trainingTime)) throw new InvalidTrainingTimeException("Invalid training time " + trainingTime);
        if (isInvalidCost(trainingCost)) throw new InvalidTrainingCostException("Invalid training cost " + trainingCost);
        if (isInvalidWeapon(weapon)) throw new InvalidWeaponException("Invalid weapon name " + weapon);
        this.trainingTime = trainingTime;
        this.trainingCost = trainingCost;
        this.weapon = weapon;
    }

    public int getTrainingTime() {
        return trainingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trooper trooper = (Trooper) o;
        return trainingTime == trooper.trainingTime && trainingCost == trooper.trainingCost && weapon == trooper.weapon;
    }
}
