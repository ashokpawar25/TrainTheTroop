package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.db.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrooperRepositoryTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    TrooperRepository trooperRepository = new TrooperRepository(inMemoryDatabase);
    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;
        boolean expected = true;

        // act
        boolean actual = trooperRepository.insert( trainingTime, trainingCost, weapon);

        // assert
        assertEquals(expected, actual);
    }
}