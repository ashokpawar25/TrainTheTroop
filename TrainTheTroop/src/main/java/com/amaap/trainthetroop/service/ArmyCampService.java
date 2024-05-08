package com.amaap.trainthetroop.service;

import com.amaap.trainthetroop.domain.model.entity.Archer;
import com.amaap.trainthetroop.domain.model.entity.Barbarian;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.ArmyCampRepository;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;

import java.util.List;

public class ArmyCampService {

    ArmyCampRepository armyCampRepository;

    public ArmyCampService(ArmyCampRepository armyCampRepository) {
        this.armyCampRepository = armyCampRepository;
    }

    public void addTrooperToCamp(Trooper trooper) {
        armyCampRepository.add(trooper);
    }

    public List<Trooper> getTrainedTroopers() {
        return armyCampRepository.getTrainedTroopers();
    }

    public long getTrooperCountFor(TroopType troopType) {
        long archerCount = 0;
        long barbarianCount = 0;
        List<Trooper> trainedTroopers = armyCampRepository.getTrainedTroopers();
        archerCount = trainedTroopers.stream()
                .filter(trooper -> trooper instanceof Archer)
                .count();

        barbarianCount = trainedTroopers.stream()
                .filter(trooper -> trooper instanceof Barbarian)
                .count();

        return troopType == TroopType.ARCHER ? archerCount : barbarianCount;
    }
}
