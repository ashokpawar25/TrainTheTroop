package com.amaap.trainthetroop.repository.db.impl;

import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;

import java.util.*;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    List<Trooper> trooperList = new ArrayList<>();
    Queue<Trooper> trooperInBarrack = new LinkedList<>();
    List<Trooper> trooperInArmyCamp = new ArrayList<>();

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
        trooperInArmyCamp.add(trooper);
    }

    @Override
    public List<Trooper> getTroopersFromArmyCamp() {
        return trooperInArmyCamp;
    }
}
