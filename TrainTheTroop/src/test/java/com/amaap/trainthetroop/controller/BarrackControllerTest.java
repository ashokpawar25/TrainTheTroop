package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.ArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.BarrackRepository;
import com.amaap.trainthetroop.repository.Impl.TrooperRepository;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryBarrackRepository;
import com.amaap.trainthetroop.service.ArmyCampService;
import com.amaap.trainthetroop.service.BarrackService;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarrackControllerTest {
    TrooperService trooperService = new TrooperService(new TrooperRepository(new FakeInMemoryDatabase()));
    InMemoryBarrackRepository inMemoryBarrackRepository = new BarrackRepository(new FakeInMemoryDatabase());
    ArmyCampService armyCampService = new ArmyCampService(new ArmyCampRepository(new FakeInMemoryDatabase()));
    BarrackService barrackService = new BarrackService(inMemoryBarrackRepository,armyCampService);
    BarrackController barrackController = new BarrackController(trooperService, barrackService);

    @Test
    void shouldBeAbleReturnOkResponseWhenTrooperAddedSuccessfullyIntoBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
        trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        Response expected = new Response(HttpStatus.OK, "Trooper added into barrack");

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        Response actual = barrackController.addTrooperToBarrack(troopers);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleReturnBadRequestResponseWhenNumberOfTroopersToAddIntoBarrackIsMoreThanBarrackSize() throws Exception, InvalidTrooperTypeException {
        // arrange
        for (int i = 0; i < 6; i++) {
            trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
            trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        }
        Response expected = new Response(HttpStatus.BADREQUEST, "Barrack is full...!\nTry after some time");

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        Response actual = barrackController.addTrooperToBarrack(troopers);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTroopersFromBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
        trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        Response actual = barrackController.addTrooperToBarrack(troopers);
        Queue<Trooper> troopersToTrain = barrackController.getTroopers();

        // assert
        assertEquals(2, troopersToTrain.size());
    }

    @Test
    void shouldBeAbleToTrainTheTrooper() throws InvalidTrooperTypeException, Exception {
        // arrange
        for (int i = 0; i < 5; i++) {
            trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
            trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        }
        long expectedTrainingTime = 45;
        int expectedCountOfTrooperInArmyCamp = 10;

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        barrackController.addTrooperToBarrack(troopers);
        LocalTime trainingStartTime = LocalTime.now();
        barrackController.trainTheTrooper();
        LocalTime trainingEndTime = LocalTime.now();
        long actualTrainingTime = Duration.between(trainingStartTime, trainingEndTime).getSeconds();
        int actualCountOfTrooperInArmyCamp = armyCampService.getTrainedTroopers().size();

        // assert
//        assertEquals(expectedTrainingTime,actualTrainingTime);
        assertEquals(expectedCountOfTrooperInArmyCamp,actualCountOfTrooperInArmyCamp);
    }
}
