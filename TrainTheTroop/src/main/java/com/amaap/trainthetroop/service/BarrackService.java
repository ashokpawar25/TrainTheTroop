package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.BarrackRepository;
import com.amaap.trainthetroop.service.exception.BarrackFullException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BarrackService {
    BarrackRepository barrackRepository;
    ArmyCampService armyCampService;

    public BarrackService(BarrackRepository barrackRepository, ArmyCampService armyCampService) {
        this.barrackRepository = barrackRepository;
        this.armyCampService = armyCampService;
    }

    public void addTroopers(List<Trooper> trooperList) throws BarrackFullException {
        Queue<Trooper> trooperQueue = new LinkedList<>();
        int queueSize = 0;
        while (queueSize < trooperList.size()) {
            if (trooperQueue.size() <= 10) {
                trooperQueue.add(trooperList.get(queueSize));
                queueSize++;
            } else {
                throw new BarrackFullException("Barrack is full...!\nTry after some time");
            }
        }
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
