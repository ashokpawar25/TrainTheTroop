package com.amaap.trainthetroop.domain.model.validator;

import com.amaap.trainthetroop.domain.model.valueobject.Weapon;

import java.util.EnumSet;

public class TrooperDataValidator {
    public static boolean isInvalidTime(int trainingTime) {
        if (trainingTime <= 0) return true;
        return false;
    }

    public static boolean isInvalidCost(int trainingCost) {
        if (trainingCost <= 0) return true;
        return false;
    }

    public static boolean isInvalidWeapon(Weapon weapon) {
        if (!EnumSet.allOf(Weapon.class).contains(weapon)) return true;
        return false;
    }
}
