package com.amaap.trainthetroop.domain.model.validator;

import com.amaap.trainthetroop.domain.model.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrooperDataValidatorTest {

    @Test
    void shouldBeAbleToReturnFalseWhenTrainingTimeIsGreaterThanZero()
    {
        // arrange
        int trainingTime = 3;

        // act
        boolean isValidTime = TrooperDataValidator.isInvalidTime(trainingTime);

        // assert
        assertFalse(isValidTime);
    }

    @Test
    void shouldBeAbleToReturnTrueWhenTrainingTimeIsLessThanOrEqualToZero()
    {
        // arrange
        int trainingTime = -4;

        // act
        boolean isValidTime = TrooperDataValidator.isInvalidTime(trainingTime);

        // assert
        assertTrue(isValidTime);
    }

    @Test
    void shouldBeAbleToReturnFalseWhenTrainingCostIsGreaterThanZero()
    {
        // arrange
        int trainingCost = 3;

        // act
        boolean isValidTime = TrooperDataValidator.isInvalidTime(trainingCost);

        // assert
        assertFalse(isValidTime);
    }

    @Test
    void shouldBeAbleToReturnTrueWhenTrainingCostIsLessThanOrEqualToZero()
    {
        // arrange
        int trainingCost = -4;

        // act
        boolean isValidTime = TrooperDataValidator.isInvalidTime(trainingCost);

        // assert
        assertTrue(isValidTime);
    }

    @Test
    void shouldBeAbleToReturnFalseWhenWeaponIsValid()
    {
        // arrange
        Weapon weapon = Weapon.SWORD;

        // act
        boolean isValidTime = TrooperDataValidator.isInvalidWeapon(weapon);

        // assert
        assertFalse(isValidTime);
    }

    @Test
    void shouldBeAbleToReturnTrueWhenWhenWeaponIsNull()
    {
        // arrange
        Weapon weapon = null;

        // act
        boolean isValidTime = TrooperDataValidator.isInvalidWeapon(weapon);

        // assert
        assertTrue(isValidTime);
    }

}