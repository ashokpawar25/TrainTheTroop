package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.repository.Impl.InMemoryArmyCampRepository;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.service.ArmyCampService;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmyCampControllerTest {

    ArmyCampController armyCampController = new ArmyCampController(new ArmyCampService(
            new InMemoryArmyCampRepository(new FakeInMemoryDatabase())));

    @Test
    void shouldBeAbleToReturnOkResponseWhenTrooperAddedIntoArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper trooper = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        Response expected = new Response(HttpStatus.OK,"Trooper added to army camp");

        // act
        Response actual = armyCampController.addTrooperToCamp(trooper);

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

    @Test
    void shouldBeAbleToGetTrainedTroopersFromArmyCamp() throws InvalidTrooperDataException {
        // arrange
        Trooper trooper1 = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        Trooper trooper2 = new Archer(6, 20, Weapon.BOW_AND_ARROW);
        List<Trooper> expected = List.of(trooper1,trooper2);

        // act
        armyCampController.addTrooperToCamp(trooper1);
        armyCampController.addTrooperToCamp(trooper2);
        List<Trooper> actual = armyCampController.getTrainedTroopers();

        // assert
        assertEquals(expected, actual);
    }


}
