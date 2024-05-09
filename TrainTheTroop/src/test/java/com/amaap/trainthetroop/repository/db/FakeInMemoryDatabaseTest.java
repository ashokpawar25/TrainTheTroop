package com.amaap.trainthetroop.repository.db;

import com.amaap.trainthetroop.AppModule;
import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.factory.TrooperFactory;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class FakeInMemoryDatabaseTest {

    FakeInMemoryDatabase fakeInMemoryDatabase;

    @BeforeEach
    void setUp()
    {
        Injector injector = Guice.createInjector(new AppModule());
        fakeInMemoryDatabase = injector.getInstance(FakeInMemoryDatabase.class);
    }

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

    @Test
    void shouldBeAbleToGetTrainedTroopersFromArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper trooper1 = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        Trooper trooper2 = new Barbarian(3, 10, Weapon.SWORD);
        List<Trooper> expectedTrainedTroopers = List.of(trooper1,trooper2);

        // act
        fakeInMemoryDatabase.insertIntoArmyCampTable(trooper1);
        fakeInMemoryDatabase.insertIntoArmyCampTable(trooper2);
        List<Trooper> actualTrainedTroopers = fakeInMemoryDatabase.getTroopersFromArmyCamp();

        // assert
        assertEquals(expectedTrainedTroopers, actualTrainedTroopers);
    }

    @Test
    void shouldBeAbleToGetTroopersWithCount() throws InvalidTrooperDataException, InsufficientTrooperCountException {
        // arrange
        for(int i = 0;i<5;i++)
        {
            fakeInMemoryDatabase.insertIntoTrooperTable(new Barbarian(3, 10, Weapon.SWORD));
            fakeInMemoryDatabase.insertIntoTrooperTable(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        }

        // act
        List<Trooper> troopers = fakeInMemoryDatabase.getTroopersWithCount(5,5);
        List<Trooper> listOfTroopersInDatabase = fakeInMemoryDatabase.getTroopers();

        // assert
        assertEquals(10,troopers.size());
        assertEquals(0 ,listOfTroopersInDatabase.size());
    }

    @Test
    void shouldThrowInsufficientTrooperCountExceptionWhenTroopersAreNotAvailable() throws InvalidTrooperDataException, InsufficientTrooperCountException {
        // arrange
        for(int i = 0;i<5;i++)
        {
            fakeInMemoryDatabase.insertIntoTrooperTable(new Barbarian(3, 10, Weapon.SWORD));
            fakeInMemoryDatabase.insertIntoTrooperTable(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        }

        // act & assert
        assertThrows(InsufficientTrooperCountException.class,()->fakeInMemoryDatabase.getTroopersWithCount(6,9));
        assertThrows(InsufficientTrooperCountException.class,()->fakeInMemoryDatabase.getTroopersWithCount(4,6));
        assertThrows(InsufficientTrooperCountException.class,()->fakeInMemoryDatabase.getTroopersWithCount(6,4));
        assertThrows(InsufficientTrooperCountException.class,()->fakeInMemoryDatabase.getTroopersWithCount(6,4));

    }
}