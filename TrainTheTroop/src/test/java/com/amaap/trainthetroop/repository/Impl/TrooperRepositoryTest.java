package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TrooperRepositoryTest {
    TrooperRepository trooperRepository = new TrooperRepository(new FakeInMemoryDatabase());

    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() throws Exception {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;
        Trooper trooper = new Archer(trainingTime, trainingCost, weapon);

        // act
        Trooper actual = trooperRepository.insert(trooper);
        boolean isArcher = actual instanceof Archer;

        // assert
        assertTrue(isArcher);
    }

    @Test
    void shouldBeAbleToCreateTrooperOfTypeBarbarian() throws Exception {
        // arrange
        int trainingTime = 3;
        int trainingCost = 10;
        Weapon weapon = Weapon.SWORD;
        Trooper trooper = new Barbarian(trainingTime, trainingCost, weapon);

        // act
        Trooper actual = trooperRepository.insert(trooper);
        boolean isArcher = actual instanceof Barbarian;

        // assert
        assertTrue(isArcher);
    }
}