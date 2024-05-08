package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
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
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarrackControllerTest {
    TrooperService trooperService = new TrooperService(new InMemoryTrooperRepository(new FakeInMemoryDatabase()));
    ArmyCampService armyCampService = new ArmyCampService(new InMemoryArmyCampRepository(new FakeInMemoryDatabase()));
    BarrackService barrackService = new BarrackService(new InMemoryBarrackRepository(new FakeInMemoryDatabase()),armyCampService,trooperService);
    BarrackController barrackController = new BarrackController(trooperService, barrackService);

    @Test
    void shouldBeAbleReturnOkResponseWhenTrooperAddedSuccessfullyIntoBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
        trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        Response expected = new Response(HttpStatus.OK, "Trooper added into barrack");

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        Response actual = barrackController.addTrooperToBarrack(1,1);

        // assert
        assertEquals(expected, actual);
    }

//    @Test
//    void shouldBeAbleReturnBadRequestResponseWhenNumberOfTroopersToAddIntoBarrackIsMoreThanBarrackSize() throws Exception, InvalidTrooperTypeException {
//        // arrange
//        for (int i = 0; i < 6; i++) {
//            trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
//            trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
//        }
//        Response expected = new Response(HttpStatus.BADREQUEST, "Barrack is full...!\nTry after some time");
//
//        // act
//        List<Trooper> troopers = trooperService.getTroopers();
//        Response actual = barrackController.addTrooperToBarrack(5,5);
//
//        // assert
//        assertEquals(expected, actual);
//    }

    @Test
    void shouldBeAbleToGetTroopersFromBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
        trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        Response actual = barrackController.addTrooperToBarrack(1,1);
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
        Response expected = new Response(HttpStatus.OK,"Trooper trained successfully");

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        barrackController.addTrooperToBarrack(5,5);
        LocalTime trainingStartTime = LocalTime.now();
        Response actual = barrackController.trainTheTrooper();
        LocalTime trainingEndTime = LocalTime.now();
        long actualTrainingTime = Duration.between(trainingStartTime, trainingEndTime).getSeconds();
        int actualCountOfTrooperInArmyCamp = armyCampService.getTrainedTroopers().size();

        // assert
//        assertEquals(expectedTrainingTime,actualTrainingTime);
        assertEquals(expectedCountOfTrooperInArmyCamp,actualCountOfTrooperInArmyCamp);
        assertEquals(expected,actual);
    }
}
