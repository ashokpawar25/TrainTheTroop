package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.InMemoryBarrackRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BarrackService {
    InMemoryBarrackRepository inMemoryBarrackRepository;

    public BarrackService(InMemoryBarrackRepository inMemoryBarrackRepository) {
        this.inMemoryBarrackRepository = inMemoryBarrackRepository;
    }

    public void addTroopers(List<Trooper> trooperList) {
        Queue<Trooper> trooperQueue = new LinkedList<>();
        int queueSize = 0;
        while (trooperQueue.size()<=10)
        {
            trooperQueue.add(trooperList.get(queueSize));
            queueSize++;
        }
        inMemoryBarrackRepository.addTroopers(trooperQueue);
    }
}
