package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.repository.Impl.TrooperRepository;
import com.amaap.trainthetroop.repository.Impl.db.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryTrooperRepository;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrooperServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    InMemoryTrooperRepository inMemoryTrooperRepository = new TrooperRepository(inMemoryDatabase);
    TrooperService trooperService = new TrooperService(inMemoryTrooperRepository);

    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() throws Exception, InvalidTrooperTypeException {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;

        // act
        Trooper actual = trooperService.create(TroopType.ARCHER, trainingTime, trainingCost, weapon);
        boolean isArcher = actual instanceof Archer;

        // assert
        assertTrue(isArcher);
    }

    @Test
    void shouldBeAbleToCreateTrooperOfTypeBarbarian() throws Exception, InvalidTrooperTypeException {
        // arrange
        int trainingTime = 3;
        int trainingCost = 10;
        Weapon weapon = Weapon.SWORD;

        // act
        Trooper actual = trooperService.create(TroopType.BARBARIAN, trainingTime, trainingCost, weapon);
        boolean isBarbarian = actual instanceof Barbarian;

        // assert
        assertTrue(isBarbarian);
    }

    @Test
    void shouldThrowInvalidTrooperTypeExceptionWhenTrooperTypeIsInvalid() throws Exception, InvalidTrooperTypeException {
        // arrange
        int trainingTime = 3;
        int trainingCost = 10;
        Weapon weapon = Weapon.SWORD;

        // assert
        assertThrows(InvalidTrooperTypeException.class,()-> trooperService.create(null,trainingTime,trainingCost,weapon));
    }

}