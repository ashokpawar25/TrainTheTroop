package com.amaap.trainthetroop.repository.Impl.db.impl;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.Impl.db.impl.exception.InsufficientTrooperCountException;

import java.util.*;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    List<Trooper> trooperList = new ArrayList<>();
    Queue<Trooper> trooperInBarrack = new LinkedList<>();
    List<Trooper> trainedTroopers = new ArrayList<>();
    int trooperI;

    @Override
    public Trooper insertIntoTrooperTable(Trooper trooper) {
        trooperList.add(trooper);
        return trooper;
    }

    @Override
    public List<Trooper> getTroopers() {
        return trooperList;
    }

    public List<Trooper> getTroopersWithCount(int archerCount,int barbarianCount) throws InsufficientTrooperCountException {
        List<Trooper> troopersToReturn = new ArrayList<>();
        Iterator<Trooper> iterator = trooperList.iterator();
        while (iterator.hasNext())
        {
            Trooper trooper = iterator.next();
            if (trooper instanceof Archer && archerCount>0) {
                troopersToReturn.add(trooper);
                iterator.remove();
                archerCount--;
            } else if(trooper instanceof Barbarian && barbarianCount>0) {
                troopersToReturn.add(trooper);
                iterator.remove();
                barbarianCount--;
            }
        }
        if(archerCount > 0 || barbarianCount>0) throw new InsufficientTrooperCountException("Required number of troopers are not available");
        return troopersToReturn;
    }

    @Override
    public void insertIntoBarrack(Queue<Trooper> trooperQueue) {
        trooperInBarrack.addAll(trooperQueue);
    }

    public Queue<Trooper> getTroopersFromBarrack() {
        return trooperInBarrack;
    }

    @Override
    public void insertIntoArmyCampTable(Trooper trooper) {
        trainedTroopers.add(trooper);
    }

    @Override
    public List<Trooper> getTroopersFromArmyCamp() {
        return trainedTroopers;
    }
}
