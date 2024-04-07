package com.amaap.trainthetroop.domain.model;

import com.amaap.trainthetroop.domain.model.exception.InvalidTrainingTimeException;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrooperTest {

    @Test
    void shouldThrowInvalidTrainingTimeExceptionWhenTimeIsInvalid() {
        // arrange
        int trainingTime = 0;
        int trainingCost = 3;
        Weapon weapon = Weapon.SWORD;

        // act & assert
        assertThrows(InvalidTrainingTimeException.class,()->new Trooper(trainingTime,trainingCost,weapon) );
    }

}