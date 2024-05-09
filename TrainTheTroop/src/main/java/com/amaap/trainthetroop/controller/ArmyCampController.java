package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.service.ArmyCampService;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;

import java.util.List;

public class ArmyCampController {
    private final ArmyCampService armyCampService;

    public ArmyCampController(ArmyCampService armyCampService) {
        this.armyCampService = armyCampService;
    }

    public Response addTrooperToCamp(Trooper trooper) {
        armyCampService.addTrooperToCamp(trooper);
        return new Response(HttpStatus.OK,"Trooper added to army camp");
    }

    public List<Trooper> getTrainedTroopers() {
        return armyCampService.getTrainedTroopers();
    }

    public long getTrooperCount(TroopType troopType) {
        return armyCampService.getTrooperCountFor(troopType);
    }
}
