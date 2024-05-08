package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrainingCostException;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrainingTimeException;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidWeaponException;
import com.amaap.trainthetroop.repository.Impl.InMemoryTrooperRepository;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrooperServiceTest {
    TrooperService trooperService = new TrooperService(new InMemoryTrooperRepository(new FakeInMemoryDatabase()));

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

        // act & assert
        assertThrows(InvalidTrooperTypeException.class, () -> trooperService.create(null, trainingTime, trainingCost, weapon));
    }

    @Test
    void shouldThrowInvalidTrainingTimeExceptionWhenTimeIsInvalid() {
        // arrange
        int trainingTime = 0;
        int trainingCost = 3;
        Weapon weapon = Weapon.SWORD;

        // act & assert
        InvalidTrainingTimeException exception = assertThrows(InvalidTrainingTimeException.class, () -> trooperService.create(TroopType.ARCHER, trainingTime, trainingCost, weapon), "Invalid training time " + trainingTime);
        assertEquals("Invalid training time " + trainingTime, exception.getMessage());
    }

    @Test
    void shouldThrowInvalidTrainingCostExceptionWhenCostIsInvalid() {
        // arrange
        int trainingTime = 3;
        int trainingCost = -3;
        Weapon weapon = Weapon.SWORD;

        // act & assert
        InvalidTrainingCostException exception = assertThrows(InvalidTrainingCostException.class, () -> trooperService.create(TroopType.BARBARIAN, trainingTime, trainingCost, weapon), "Invalid training cost " + trainingCost);
        assertEquals("Invalid training cost " + trainingCost, exception.getMessage());

    }

    @Test
    void shouldThrowInvalidWeaponExceptionWhenWeaponIsInvalid() {
        // arrange
        int trainingTime = 3;
        int trainingCost = 20;
        Weapon weapon = null;

        // act & assert
        InvalidWeaponException exception = assertThrows(InvalidWeaponException.class, () -> trooperService.create(TroopType.BARBARIAN, trainingTime, trainingCost, weapon), "Invalid weapon name " + weapon);
        assertEquals("Invalid weapon name " + weapon, exception.getMessage());

    }

    @Test
    void getTroopersWithCount() throws Exception, InvalidTrooperTypeException {
        // arrange
        for(int i = 0;i<5;i++)
        {
            trooperService.create(TroopType.BARBARIAN,3,10,Weapon.SWORD);
            trooperService.create(TroopType.ARCHER,6,20,Weapon.BOW_AND_ARROW);
        }

        // act
        List<Trooper> troopers = trooperService.getTroopersWithCount(5,5);
        List<Trooper> listOfTroopersInDatabase = trooperService.getTroopers();

        // assert
        assertEquals(10,troopers.size());
        assertEquals(0 ,listOfTroopersInDatabase.size());
    }

}