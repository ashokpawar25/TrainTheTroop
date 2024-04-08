package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.factory.TrooperFactory;
import com.amaap.trainthetroop.repository.Impl.InMemoryArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryBarrackRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryTrooperRepository;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.service.exception.BarrackFullException;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BarrackServiceTest {
    TrooperService trooperService = new TrooperService(new InMemoryTrooperRepository(new FakeInMemoryDatabase()));

    ArmyCampService armyCampService = new ArmyCampService(new InMemoryArmyCampRepository(new FakeInMemoryDatabase()));
    BarrackService barrackService = new BarrackService(new InMemoryBarrackRepository(new FakeInMemoryDatabase()), armyCampService,trooperService);

    @Test
    void shouldBeAbleToAddTrooperIntoBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        for(int i = 0;i<5;i++)
        {
            trooperService.create(TroopType.BARBARIAN,3,10, Weapon.SWORD);
            trooperService.create(TroopType.ARCHER,6,20,Weapon.BOW_AND_ARROW);
        }

        // act
        barrackService.addTroopers(5,5);
        Queue<Trooper> troopersInBarrack = barrackService.getTroopersFromBarrack();

        // assert
        assertEquals(10, troopersInBarrack.size());
    }

    @Test
    void shouldBeAbleToTrainTheTrooper() throws Exception, InvalidTrooperTypeException {
        // arrange
        for (int i = 0; i < 5; i++) {
            trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
            trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        }
        int expectedTrainingTime = 45;
        int expectedCountOfTrooperInArmyCamp = 10;

        // act
        barrackService.addTroopers(5,5);
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