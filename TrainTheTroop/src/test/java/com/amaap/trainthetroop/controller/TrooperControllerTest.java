package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.TrooperRepository;
import com.amaap.trainthetroop.repository.Impl.db.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryTrooperRepository;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrooperControllerTest {

    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    InMemoryTrooperRepository inMemoryTrooperRepository = new TrooperRepository(inMemoryDatabase);
    TrooperService trooperService = new TrooperService(inMemoryTrooperRepository);
    TrooperController trooperController = new TrooperController(trooperService);

    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() {
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
}
