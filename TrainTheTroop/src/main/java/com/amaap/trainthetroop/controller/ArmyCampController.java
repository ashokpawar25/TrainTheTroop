package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.service.ArmyCampService;
import com.amaap.trainthetroop.service.model.TroopType;

import java.util.List;

public class ArmyCampController {
    ArmyCampService armyCampService;

    public ArmyCampController(ArmyCampService armyCampService) {
        this.armyCampService = armyCampService;
    }

    public List<Trooper> getTrainedTroopers() {
        return armyCampService.getTrainedTroopers();
    }

    public void addTrooperToCamp(Trooper trooper) {
        armyCampService.addTrooperToCamp(trooper);
    }

    public long getTrooperCount(TroopType troopType) {
        return armyCampService.getTrooperCountFor(troopType);
    }
}
