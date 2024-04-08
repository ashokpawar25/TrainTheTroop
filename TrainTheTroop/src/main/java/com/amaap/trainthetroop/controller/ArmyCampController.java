package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
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

    public Response addTrooperToCamp(Trooper trooper) {
        armyCampService.addTrooperToCamp(trooper);
        return new Response(HttpStatus.OK,"Trooper added to camp");
    }

    public long getTrooperCount(TroopType troopType) {
        return armyCampService.getTrooperCountFor(troopType);
    }
}
