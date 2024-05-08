package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryArmyCampRepositoryTest {

    InMemoryArmyCampRepository inMemoryArmyCampRepository = new InMemoryArmyCampRepository(new FakeInMemoryDatabase());
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
        List<Trooper> expectedTrainedTroopers = List.of(trooper1,trooper2);

        // act
        inMemoryArmyCampRepository.add(trooper1);
        inMemoryArmyCampRepository.add(trooper2);
        List<Trooper> actualTrainedTroopers = inMemoryArmyCampRepository.getTrainedTroopers();

        // assert
        assertEquals(expectedTrainedTroopers, actualTrainedTroopers);
    }
}