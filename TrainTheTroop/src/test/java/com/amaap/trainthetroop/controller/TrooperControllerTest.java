package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.TrooperRepository;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.amaap.trainthetroop.domain.model.factory.TrooperFactory.getTrooperList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrooperControllerTest {

    TrooperController trooperController = new TrooperController(new TrooperService(
            new TrooperRepository(new FakeInMemoryDatabase())));

    @Test
    void shouldBeAbleToReturnOkResponseWhenCreatesTrooperOfTypeArcher() throws Exception, InvalidTrooperTypeException {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;
        Response expected = new Response(HttpStatus.OK, "Trooper created successfully");

        // act
        Response actual = trooperController.create(TroopType.ARCHER, trainingTime, trainingCost, weapon);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToReturnOkResponseWhenCreatesTrooperOfTypeBarbarian() throws Exception, InvalidTrooperTypeException {
        // arrange
        int trainingTime = 3;
        int trainingCost = 10;
        Weapon weapon = Weapon.SWORD;
        Response expected = new Response(HttpStatus.OK, "Trooper created successfully");

        // act
        Response actual = trooperController.create(TroopType.BARBARIAN, trainingTime, trainingCost, weapon);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnResponseAsBadRequestWhenTrooperTypeIsInvalid()
            throws Exception, InvalidTrooperTypeException {
        // arrange
        int trainingTime = 3;
        int trainingCost = 10;
        Weapon weapon = Weapon.SWORD;
        Response expected = new Response(HttpStatus.BADREQUEST, "Invalid trooper type: null");

        // act
        Response actual = trooperController.create(null, trainingTime, trainingCost, weapon);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTroopers() throws Exception, InvalidTrooperTypeException {
        // arrange
        trooperController.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
        trooperController.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        List<Trooper>  expected = getTrooperList();

        // act
        List<Trooper> actual = trooperController.getTroopers();

        // assert
        assertEquals(expected, actual);
    }
}
