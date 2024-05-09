package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.AppModule;
import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryTrooperRepositoryTest {
    InMemoryTrooperRepository inMemoryTrooperRepository;
    @BeforeEach
    void setUp()
    {
        Injector injector = Guice.createInjector(new AppModule());
        inMemoryTrooperRepository = injector.getInstance(InMemoryTrooperRepository.class);
    }
    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() throws Exception {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;
        Trooper trooper = new Archer(trainingTime, trainingCost, weapon);

        // act
        Trooper actual = inMemoryTrooperRepository.insert(trooper);
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
        Trooper actual = inMemoryTrooperRepository.insert(trooper);
        boolean isArcher = actual instanceof Barbarian;

        // assert
        assertTrue(isArcher);
    }

    @Test
    void shouldBeAbleToGetTroopersWithCount() throws InvalidTrooperDataException, InsufficientTrooperCountException {
        // arrange
        for(int i = 0;i<5;i++)
        {
            inMemoryTrooperRepository.insert(new Barbarian(3, 10, Weapon.SWORD));
            inMemoryTrooperRepository.insert(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        }

        // act
        List<Trooper> troopers = inMemoryTrooperRepository.getTroopersWithCount(5,5);
        List<Trooper> listOfTroopersInDatabase = inMemoryTrooperRepository.getTroopers();

        // assert
        assertEquals(10,troopers.size());
        assertEquals(0 ,listOfTroopersInDatabase.size());
    }

}