package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.ArmyCampRepository;
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