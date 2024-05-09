package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.repository.Impl.InMemoryArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryBarrackRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryTrooperRepository;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.service.ArmyCampService;
import com.amaap.trainthetroop.service.BarrackService;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarrackControllerTest {
    TrooperService trooperService = new TrooperService(new InMemoryTrooperRepository(new FakeInMemoryDatabase()));
    ArmyCampService armyCampService = new ArmyCampService(new InMemoryArmyCampRepository(new FakeInMemoryDatabase()));
    BarrackService barrackService = new BarrackService(new InMemoryBarrackRepository(new FakeInMemoryDatabase()),armyCampService,trooperService);
    BarrackController barrackController = new BarrackController(barrackService);

    @Test
    void shouldBeAbleToGetOkResponseWhenTrooperAddedSuccessfullyIntoBarrack() throws InvalidTrooperTypeException, InvalidTrooperDataException {
        // arrange
        trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
        trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        Response expected = new Response(HttpStatus.OK, "Trooper added into barrack");

        // act
        Response actual = barrackController.addTrooperToBarrack(1,1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleGetBadRequestResponseWhenNumberOfTroopersToAddIntoBarrackIsMoreThanBarrackSize() throws Exception, InvalidTrooperTypeException {
        // arrange
        for (int i = 0; i < 6; i++) {
            trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
            trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        }
        Response expected = new Response(HttpStatus.BADREQUEST, "Maximum ten troopers can be added into barrack at a time");

        // act
        Response actual = barrackController.addTrooperToBarrack(6,6);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTroopersFromBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        Trooper trooper1 = trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
        Trooper trooper2 = trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        List<Trooper> troopers = List.of(trooper1,trooper2);
        Queue<Trooper> expected = new LinkedList<>(troopers);

        // act
        barrackController.addTrooperToBarrack(1,1);
        Queue<Trooper> actual = barrackController.getTroopers();

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToTrainTheTrooper() throws InvalidTrooperTypeException, Exception {
        // arrange
        for (int i = 0; i < 5; i++) {
            trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
            trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        }
        long expectedTrainingTime = 45;
        Response expected = new Response(HttpStatus.OK,"Trooper trained successfully");

        // act
        barrackController.addTrooperToBarrack(5,5);
        LocalTime trainingStartTime = LocalTime.now();
        Response actual = barrackController.trainTheTrooper();
        LocalTime trainingEndTime = LocalTime.now();
        long actualTrainingTime = Duration.between(trainingStartTime, trainingEndTime).getSeconds();

        // assert
        assertEquals(expectedTrainingTime,actualTrainingTime);
        assertEquals(expected,actual);
    }
}
