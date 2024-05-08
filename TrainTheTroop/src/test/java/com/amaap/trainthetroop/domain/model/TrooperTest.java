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

    @Test
    void shouldBeAbleToCheckEqualityOfTroopersInstance() throws InvalidTrooperDataException {

        // arrange
        Trooper trooper1 = new Trooper(3, 10, Weapon.SWORD);
        Trooper trooper2 = new Trooper(3, 10, Weapon.SWORD);
        Trooper trooper3 = new Trooper(6, 10, Weapon.SWORD);
        Trooper trooper4 = new Trooper(3, 20, Weapon.SWORD);
        Trooper trooper5 = new Trooper(3, 10, Weapon.BOW_AND_ARROW);
        Trooper trooper6 = new Trooper(6, 20, Weapon.BOW_AND_ARROW);

        // act & assert
        assertTrue(trooper1.equals(trooper1));
        assertTrue(trooper1.equals(trooper2));
        assertFalse(trooper1.equals(trooper3));
        assertFalse(trooper1.equals(trooper4));
        assertFalse(trooper1.equals(trooper5));
        assertFalse(trooper1.equals(trooper6));
        assertFalse(trooper1.equals(object));

        Trooper trooper2 = new Trooper(3, 10, Weapon.BOW_AND_ARROW);
        Trooper trooper3 = new Trooper(6, 20, Weapon.BOW_AND_ARROW);
        Trooper trooper4 = new Trooper(6, 20, Weapon.SWORD);
        Trooper trooper5 = new Trooper(6, 20, Weapon.SWORD);
        Trooper trooper6 = new Trooper(3, 20, Weapon.SWORD);

        // act & assert
        assertTrue(trooper1.equals(trooper1));
        assertTrue(trooper1.equals(trooper2));
        assertFalse(trooper1.equals(trooper3));
        assertFalse(trooper1.equals(trooper4));
        assertFalse(trooper1.equals(trooper5));
        assertFalse(trooper1.equals(trooper6));
        assertFalse(trooper1.equals(object));
        assertFalse(trooper1.equals(null));
    }
}