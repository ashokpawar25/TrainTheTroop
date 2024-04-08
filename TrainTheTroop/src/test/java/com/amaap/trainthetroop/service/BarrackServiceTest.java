package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.factory.TrooperFactory;
import com.amaap.trainthetroop.repository.Impl.ArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.BarrackRepository;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryBarrackRepository;
import com.amaap.trainthetroop.service.exception.BarrackFullException;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BarrackServiceTest {

    ArmyCampService armyCampService = new ArmyCampService(new ArmyCampRepository(new FakeInMemoryDatabase()));
    BarrackService barrackService = new BarrackService(new BarrackRepository(new FakeInMemoryDatabase()), armyCampService);

    @Test
    void shouldBeAbleToAddTrooperIntoBarrack() throws InvalidTrooperDataException, BarrackFullException {
        // arrange
        Queue<Trooper> trooperQueue = TrooperFactory.getTroopers();
        int archerCount = 0;
        int barbarianCount = 0;

        // act
        barrackService.addTroopers(trooperQueue.stream().toList());
        Queue<Trooper> troopersInBarrack = barrackService.getTroopersFromBarrack();
        for (Trooper trooper : troopersInBarrack) {
            if (trooper instanceof Archer) {
                archerCount++;
            } else if (trooper instanceof Barbarian) {
                barbarianCount++;
            }
        }

        // assert
        assertEquals(5, archerCount);
        assertEquals(5, barbarianCount);
        assertEquals(10, troopersInBarrack.size());
    }

    @Test
    void shouldBeAbleToTrainTheTrooper() throws BarrackFullException, InterruptedException, InvalidTrooperDataException {
        // arrange
        Queue<Trooper> trooperQueue = TrooperFactory.getTroopers();
        int expectedTrainingTime = 45;
        int expectedCountOfTrooperInArmyCamp = 10;

        // act
        barrackService.addTroopers(trooperQueue.stream().toList());
        LocalTime trainingStartTime = LocalTime.now();
        barrackService.trainTheTrooper();
        LocalTime trainingEndTime = LocalTime.now();
        long actualTrainingTime = Duration.between(trainingStartTime, trainingEndTime).getSeconds();
        int actualCountOfTrooperInArmyCamp = armyCampService.getTrainedTroopers().size();

        // assert
        assertEquals(expectedTrainingTime,actualTrainingTime);
        assertEquals(expectedCountOfTrooperInArmyCamp,actualCountOfTrooperInArmyCamp);
    }
}