package com.amaap.trainthetroop.repository.Impl.db;

import com.amaap.trainthetroop.domain.model.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakeInMemoryDatabaseTest {

    FakeInMemoryDatabase fakeInMemoryDatabase = new FakeInMemoryDatabase();
    @Test
    void shouldBeAbleToCreateTrooperOfTypeArcher() {
        // arrange
        int trainingTime = 6;
        int trainingCost = 20;
        Weapon weapon = Weapon.BOW_AND_ARROW;
        boolean expected = true;

        // act
        boolean actual = fakeInMemoryDatabase.insertIntoTrooperTable( trainingTime, trainingCost, weapon);

        // assert
        assertEquals(expected, actual);
    }

}