package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.repository.Impl.InMemoryArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.ArmyCampRepository;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmyCampServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    ArmyCampRepository armyCampRepository = new InMemoryArmyCampRepository(inMemoryDatabase);
    ArmyCampService armyCampService = new ArmyCampService(armyCampRepository);

    @Test
    void shouldBeAbleToAddTrooperInArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper expected = new Archer(6,20, Weapon.BOW_AND_ARROW);

        // act
        armyCampService.addTrooperToCamp(expected);
        List<Trooper> trainedTroopers = armyCampService.getTrainedTroopers();
        Trooper actual = trainedTroopers.get(0);


        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetCountOfSpecificTypeOfTrooper() throws InvalidTrooperDataException {
        // arrange
        Trooper archer1 = new Archer(6,20, Weapon.BOW_AND_ARROW);
        Trooper archer2 = new Archer(6,20, Weapon.BOW_AND_ARROW);
        Trooper barbarian1 = new Barbarian(3,10, Weapon.SWORD);
        Trooper barbarian2 = new Barbarian(3,10, Weapon.SWORD);
        long expectedArcherCount = 2;
        long expectedBarbarianCount = 2;

        // act
        armyCampService.addTrooperToCamp(archer1);
        armyCampService.addTrooperToCamp(archer2);
        armyCampService.addTrooperToCamp(barbarian1);
        armyCampService.addTrooperToCamp(barbarian2);
        long actualArcherCount = armyCampService.getTrooperCountFor(TroopType.ARCHER);
        long actualBarbarianCount = armyCampService.getTrooperCountFor(TroopType.BARBARIAN);

        // assert
        assertEquals(expectedArcherCount,actualArcherCount);
        assertEquals(expectedBarbarianCount,actualBarbarianCount);

    }

    @Test
    void shouldBeAbleToGetTrainedTroopersFromArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper trooper1 = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        Trooper trooper2 = new Barbarian(3, 10, Weapon.SWORD);
        List<Trooper> expectedTrainedTroopers = List.of(trooper1,trooper2);

        // act
        armyCampService.addTrooperToCamp(trooper1);
        armyCampService.addTrooperToCamp(trooper2);
        List<Trooper> actualTrainedTroopers = armyCampService.getTrainedTroopers();

        // assert
        assertEquals(expectedTrainedTroopers, actualTrainedTroopers);
    }
}