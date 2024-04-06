package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.TrooperRepository;
import com.amaap.trainthetroop.repository.Impl.db.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryTrooperRepository;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrooperServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    InMemoryTrooperRepository inMemoryTrooperRepository = new TrooperRepository(inMemoryDatabase);
    TrooperService trooperService = new TrooperService(inMemoryTrooperRepository);

    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;
        boolean expected = true;

        // act
        boolean actual = trooperService.create(TroopType.ARCHER, trainingTime, trainingCost, weapon);

        // assert
        assertEquals(expected, actual);
    }

}