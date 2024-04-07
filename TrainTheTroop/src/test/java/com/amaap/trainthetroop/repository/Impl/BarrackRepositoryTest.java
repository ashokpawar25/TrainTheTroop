package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.factory.TrooperFactory;
import com.amaap.trainthetroop.repository.Impl.db.impl.FakeInMemoryDatabase;
import com.amaap.trainthetroop.repository.InMemoryBarrackRepository;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BarrackRepositoryTest {
    InMemoryBarrackRepository inMemoryBarrackRepository = new BarrackRepository(new FakeInMemoryDatabase());

    @Test
    void shouldBeAbleToAddTrooperIntoBarrack() throws InvalidTrooperDataException {
        // arrange
        Queue<Trooper> trooperQueue = TrooperFactory.getTroopers();
        int archerCount = 0;
        int barbarianCount = 0;

        // act
        inMemoryBarrackRepository.addTroopers(trooperQueue);
        Queue<Trooper> troopersInBarrack = inMemoryBarrackRepository.getTroopersFromBarrack();
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

}