package com.amaap.trainthetroop.domain.model;

import com.amaap.trainthetroop.domain.model.exception.InvalidTrainingCostException;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrainingTimeException;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.exception.InvalidWeaponException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrooperTest {

    @Test
    void shouldBeAbleToCreateTrooperWhenPassedDataIsValid() throws InvalidTrooperDataException {
        // arrange
        int trainingTime = 3;
        int trainingCost = 10;
        Weapon weapon = Weapon.SWORD;

        // act
        Trooper expected = new Trooper(trainingTime, trainingCost, weapon);

        // assert
        assertNotNull(expected);
    }

    @Test
    void shouldThrowInvalidTrainingTimeExceptionWhenTimeIsInvalid() {
        // arrange
        int trainingTime = 0;
        int trainingCost = 3;
        Weapon weapon = Weapon.SWORD;

        // act & assert
        InvalidTrainingTimeException exception = assertThrows(InvalidTrainingTimeException.class, () -> new Trooper(trainingTime, trainingCost, weapon), "Invalid training time " + trainingTime);
        assertEquals("Invalid training time " + trainingTime, exception.getMessage());
    }

    @Test
    void shouldThrowInvalidTrainingCostExceptionWhenCostIsInvalid() {
        // arrange
        int trainingTime = 3;
        int trainingCost = -3;
        Weapon weapon = Weapon.SWORD;

        // act & assert
        InvalidTrainingCostException exception = assertThrows(InvalidTrainingCostException.class, () -> new Trooper(trainingTime, trainingCost, weapon), "Invalid training cost " + trainingCost);
        assertEquals("Invalid training cost " + trainingCost, exception.getMessage());

    }

    @Test
    void shouldThrowInvalidWeaponExceptionWhenWeaponIsInvalid() {
        // arrange
        int trainingTime = 3;
        int trainingCost = 20;
        Weapon weapon = null;

        // act & assert
        InvalidWeaponException exception = assertThrows(InvalidWeaponException.class, () -> new Trooper(trainingTime, trainingCost, weapon), "Invalid weapon name " + weapon);
        assertEquals("Invalid weapon name " + weapon, exception.getMessage());

    }
}