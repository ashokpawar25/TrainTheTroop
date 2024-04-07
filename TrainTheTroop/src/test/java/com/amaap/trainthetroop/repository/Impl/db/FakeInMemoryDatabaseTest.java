package com.amaap.trainthetroop.repository.Impl.db;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.factory.TrooperFactory;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FakeInMemoryDatabaseTest {

    FakeInMemoryDatabase fakeInMemoryDatabase = new FakeInMemoryDatabase();

    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() throws Exception {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;
        Trooper trooper = new Archer(trainingTime, trainingCost, weapon);

        // act
        Trooper actual = fakeInMemoryDatabase.insertIntoTrooperTable(trooper);
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
        Trooper actual = fakeInMemoryDatabase.insertIntoTrooperTable(trooper);
        boolean isArcher = actual instanceof Barbarian;

        // assert
        assertTrue(isArcher);
    }

    @Test
    void shouldBeAbleToAddTrooperIntoBarrack() throws InvalidTrooperDataException {
        // arrange
        Queue<Trooper> trooperQueue = TrooperFactory.getTroopers();
        int archerCount = 0;
        int barbarianCount = 0;

        // act
        fakeInMemoryDatabase.insertIntoBarrack(trooperQueue);
        Queue<Trooper> troopersInBarrack = fakeInMemoryDatabase.getTroopersFromBarrack();
        for (Trooper trooper : troopersInBarrack) {
            if (trooper instanceof Archer) {
                archerCount++;
            } else if (trooper instanceof Barbarian) {
                barbarianCount++;
            }
        }

        // assert
        assertEquals(5, archerCount);
        assertEquals(5, barbarianCount);
        assertEquals(10, troopersInBarrack.size());
    }

    @Test
    void shouldBeAbleToAddTrooperInArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper expected = new Archer(6,20, Weapon.BOW_AND_ARROW);

        // act
        fakeInMemoryDatabase.insertIntoArmyCampTable(expected);
        List<Trooper> trainedTroopers = fakeInMemoryDatabase.getTroopersFromArmyCamp();
        Trooper actual = trainedTroopers.get(0);

        // assert
        assertEquals(expected,actual);
    }
}