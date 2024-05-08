package com.amaap.trainthetroop.repository.Impl;

import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.factory.TrooperFactory;
import com.amaap.trainthetroop.repository.BarrackRepository;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryBarrackRepositoryTest {
    BarrackRepository barrackRepository = new InMemoryBarrackRepository(new FakeInMemoryDatabase());

    @Test
    void shouldBeAbleToAddTrooperIntoBarrack() throws InvalidTrooperDataException {
        // arrange
        Queue<Trooper> trooperQueue = TrooperFactory.getTroopers();
        int archerCount = 0;
        int barbarianCount = 0;

        // act
        barrackRepository.addTroopers(trooperQueue);
        Queue<Trooper> troopersInBarrack = barrackRepository.getTroopersFromBarrack();
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