package com.amaap.trainthetroop.repository.Impl.db.impl;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.InMemoryDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
