package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.BarrackRepository;
import com.amaap.trainthetroop.repository.Impl.db.impl.exception.InsufficientTrooperCountException;
import com.amaap.trainthetroop.service.exception.BarrackFullException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BarrackService {
    BarrackRepository barrackRepository;
    ArmyCampService armyCampService;
    TrooperService trooperService;

    public BarrackService(BarrackRepository barrackRepository, ArmyCampService armyCampService,TrooperService trooperService) {
        this.barrackRepository = barrackRepository;
        this.armyCampService = armyCampService;
        this.trooperService = trooperService;
    }

    public void addTroopers(int archerCount,int barbarianCount) throws BarrackFullException, InsufficientTrooperCountException {
        List<Trooper> troopersToTrain = trooperService.getTroopersWithCount(archerCount,barbarianCount);
        Queue<Trooper> trooperQueue = new LinkedList<>();
        trooperQueue.addAll(troopersToTrain);
        barrackRepository.addTroopers(trooperQueue);
    }

    public Queue<Trooper> getTroopersFromBarrack() {
        return barrackRepository.getTroopersFromBarrack();
    }

    public synchronized void trainTheTrooper() throws InterruptedException {
        Queue<Trooper> troopersFromBarrack = barrackRepository.getTroopersFromBarrack();

        while (!troopersFromBarrack.isEmpty()) {
            Trooper trooper = troopersFromBarrack.poll();
            int timeToTrain = trooper.getTrainingTime();
            Thread.sleep(timeToTrain * 1000L);
            armyCampService.addTrooperToCamp(trooper);
        }
    }
}
