package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryArmyCampRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmyCampRepositoryTest {

    InMemoryArmyCampRepository inMemoryArmyCampRepository = new ArmyCampRepository(new FakeInMemoryDatabase());
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
}