package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.AppModule;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.repository.Impl.InMemoryArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryBarrackRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryTrooperRepository;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;
import com.amaap.trainthetroop.service.exception.BarrackSizeFullException;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BarrackServiceTest {
    TrooperService trooperService ;
    BarrackService barrackService ;
    @BeforeEach
    void setUp()
    {
        Injector injector = Guice.createInjector(new AppModule());
        barrackService = injector.getInstance(BarrackService.class);
        trooperService = injector.getInstance(TrooperService.class);
    }

    @Test
    void shouldBeAbleToAddTrooperIntoBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        for(int i = 0;i<5;i++)
        {
            trooperService.create(TroopType.BARBARIAN,3,10, Weapon.SWORD);
            trooperService.create(TroopType.ARCHER,6,20,Weapon.BOW_AND_ARROW);
        }
        int expected = 10;

        // act
        barrackService.addTroopers(5,5);
        Queue<Trooper> troopersInBarrack = barrackService.getTroopersFromBarrack();
        int actual = troopersInBarrack.size();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToTrainTheTrooper() throws InvalidTrooperTypeException, InvalidTrooperDataException, BarrackSizeFullException, InsufficientTrooperCountException, InterruptedException {
        // arrange
        for (int i = 0; i < 5; i++) {
            trooperService.create(TroopType.ARCHER, 6, 20, Weapon.BOW_AND_ARROW);
            trooperService.create(TroopType.BARBARIAN, 3, 10, Weapon.SWORD);
        }
        long expected = 45;

        // act
        barrackService.addTroopers(5,5);
        LocalTime trainingStartTime = LocalTime.now();
        barrackService.trainTheTrooper();
        LocalTime trainingEndTime = LocalTime.now();
        long actual = Duration.between(trainingStartTime, trainingEndTime).getSeconds();

        // assert
        assertEquals(expected,actual);
    }

}