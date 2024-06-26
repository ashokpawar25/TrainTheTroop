package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.AppModule;
import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryArmyCampRepositoryTest {

    InMemoryArmyCampRepository inMemoryArmyCampRepository;

    @BeforeEach
    void setUp()
    {
        Injector injector = Guice.createInjector(new AppModule());
        inMemoryArmyCampRepository = injector.getInstance(InMemoryArmyCampRepository.class);
    }
    @Test
    void shouldBeAbleToAddTrooperInArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper expected = new Archer(6,20, Weapon.BOW_AND_ARROW);

        // act
        inMemoryArmyCampRepository.add(expected);
        List<Trooper> trainedTroopers = inMemoryArmyCampRepository.getTrainedTroopers();
        Trooper actual = trainedTroopers.get(0);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetTrainedTroopersFromArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper trooper1 = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        Trooper trooper2 = new Barbarian(3, 10, Weapon.SWORD);
        List<Trooper> expected = List.of(trooper1,trooper2);

        // act
        inMemoryArmyCampRepository.add(trooper1);
        inMemoryArmyCampRepository.add(trooper2);
        List<Trooper> actual = inMemoryArmyCampRepository.getTrainedTroopers();

        // assert
        assertEquals(expected, actual);
    }
}