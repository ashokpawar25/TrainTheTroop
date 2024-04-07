package com.amaap.trainthetroop.domain.model;

import com.amaap.trainthetroop.domain.model.exception.InvalidTrainingCostException;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrainingTimeException;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.exception.InvalidWeaponException;
import com.amaap.trainthetroop.domain.model.validator.TrooperDataValidator;

import java.util.Objects;

public class Trooper {
    private final int trainingTime;
    private final int trainingCost;
    private final Weapon weapon;

    public Trooper(int trainingTime, int trainingCost, Weapon weapon) throws InvalidTrooperDataException {
        if (TrooperDataValidator.isInvalidTime(trainingTime))
            throw new InvalidTrainingTimeException("Invalid training time " + trainingTime);
        if (TrooperDataValidator.isInvalidCost(trainingCost))
            throw new InvalidTrainingCostException("Invalid training cost " + trainingCost);
        if (TrooperDataValidator.isInvalidWeapon(weapon))
            throw new InvalidWeaponException("Invalid weapon name " + weapon);
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

    @Override
    public int hashCode() {
        return Objects.hash(trainingTime, trainingCost, weapon);
    }
}
