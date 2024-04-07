package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.repository.Impl.ArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.service.ArmyCampService;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmyCampControllerTest {

    ArmyCampController armyCampController = new ArmyCampController(new ArmyCampService(
            new ArmyCampRepository(new FakeInMemoryDatabase())));

    @Test
    void shouldBeAbleToAddTrooperInArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper expected = new Archer(6, 20, Weapon.BOW_AND_ARROW);

        // act
        armyCampController.addTrooperToCamp(expected);
        List<Trooper> trainedTroopers = armyCampController.getTrainedTroopers();
        Trooper actual = trainedTroopers.get(0);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCountOfSpecificTypeOfTrooper() throws InvalidTrooperDataException {
        // arrange
        Trooper archer1 = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        Trooper archer2 = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        Trooper barbarian1 = new Barbarian(3, 10, Weapon.SWORD);
        Trooper barbarian2 = new Barbarian(3, 10, Weapon.SWORD);
        long expectedArcherCount = 2;
        long expectedBarbarianCount = 2;

        // act
        armyCampController.addTrooperToCamp(archer1);
        armyCampController.addTrooperToCamp(archer2);
        armyCampController.addTrooperToCamp(barbarian1);
        armyCampController.addTrooperToCamp(barbarian2);
        long actualArcherCount = armyCampController.getTrooperCount(TroopType.ARCHER);
        long actualBarbarianCount = armyCampController.getTrooperCount(TroopType.BARBARIAN);

        // assert
        assertEquals(expectedArcherCount, actualArcherCount);
        assertEquals(expectedBarbarianCount, actualBarbarianCount);

    }

}
