package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.InMemoryBarrackRepository;
import com.amaap.trainthetroop.service.exception.BarrackFullException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BarrackService {
    InMemoryBarrackRepository inMemoryBarrackRepository;

    public BarrackService(InMemoryBarrackRepository inMemoryBarrackRepository) {
        this.inMemoryBarrackRepository = inMemoryBarrackRepository;
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
        inMemoryBarrackRepository.addTroopers(trooperQueue);
    }

    public Queue<Trooper> getTroopersFromBarrack() {
        return inMemoryBarrackRepository.getTroopersFromBarrack();
    }
}
