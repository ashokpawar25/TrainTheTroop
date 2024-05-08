package com.amaap.trainthetroop.domain.model.entity.validator;

import com.amaap.trainthetroop.domain.model.valueobject.Weapon;

import java.util.EnumSet;

public class TrooperDataValidator {
    public static boolean isInvalidTime(int trainingTime) {
        return trainingTime <= 0;
    }

    public static boolean isInvalidCost(int trainingCost) {
        return trainingCost <= 0;
    }

    public static boolean isInvalidWeapon(Weapon weapon) {
        return !EnumSet.allOf(Weapon.class).contains(weapon);
    }
}
