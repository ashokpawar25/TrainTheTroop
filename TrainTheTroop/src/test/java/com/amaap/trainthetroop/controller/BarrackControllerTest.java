package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.factory.TrooperFactory;
import com.amaap.trainthetroop.repository.Impl.BarrackRepository;
import com.amaap.trainthetroop.repository.Impl.TrooperRepository;
import com.amaap.trainthetroop.repository.Impl.db.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryBarrackRepository;
import com.amaap.trainthetroop.repository.InMemoryTrooperRepository;
import com.amaap.trainthetroop.service.BarrackService;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

public class BarrackControllerTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    InMemoryTrooperRepository inMemoryTrooperRepository = new TrooperRepository(inMemoryDatabase);
    TrooperService trooperService = new TrooperService(inMemoryTrooperRepository);
    InMemoryBarrackRepository inMemoryBarrackRepository= new BarrackRepository(inMemoryDatabase);
    BarrackService barrackService = new BarrackService(inMemoryBarrackRepository);
    BarrackController barrackController = new BarrackController(trooperService,barrackService);

    @Test
    void shouldBeAbleAddTrooperIntoBarrack() throws Exception, InvalidTrooperTypeException {
        // arrange
        trooperService.create(TroopType.ARCHER,6,20, Weapon.BOW_AND_ARROW);
        trooperService.create(TroopType.BARBARIAN,3,10,Weapon.SWORD);

        // act
        List<Trooper> troopers = trooperService.getTroopers();
        barrackController.addTrooperToBarrack(troopers);
        Queue<Trooper> troopersToTrain = barrackController.getTroopers();

        // assert

    }
}
