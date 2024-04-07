package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.InMemoryArmyCampRepository;
import com.amaap.trainthetroop.service.model.TroopType;

import java.util.List;

public class ArmyCampService {

    InMemoryArmyCampRepository inMemoryArmyCampRepository;

    public ArmyCampService(InMemoryArmyCampRepository inMemoryArmyCampRepository) {
        this.inMemoryArmyCampRepository = inMemoryArmyCampRepository;
    }

    public void addTrooperToCamp(Trooper trooper) {
        inMemoryArmyCampRepository.add(trooper);
    }

    public List<Trooper> getTrainedTroopers() {
        return inMemoryArmyCampRepository.getTrainedTroopers();
    }

    public long getTrooperCountFor(TroopType troopType) {
        long archerCount = 0;
        long barbarianCount = 0;
        List<Trooper> trainedTroopers = inMemoryArmyCampRepository.getTrainedTroopers();
        archerCount = trainedTroopers.stream()
                .filter(trooper -> trooper instanceof Archer)
                .count();

        barbarianCount = trainedTroopers.stream()
                .filter(trooper -> trooper instanceof Barbarian)
                .count();

        return troopType == TroopType.ARCHER ? archerCount : barbarianCount;
    }
}
