package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.repository.Impl.db.impl.exception.InsufficientTrooperCountException;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.service.model.TroopType;

import java.util.List;

public class TrooperController {
    private final TrooperService trooperService;

    public TrooperController(TrooperService trooperService) {
        this.trooperService = trooperService;
    }

    public Response create(TroopType type, int trainingTime, int trainingCost, Weapon weapon) throws Exception, InvalidTrooperTypeException {
        try {
            trooperService.create(type, trainingTime, trainingCost, weapon);
            return new Response(HttpStatus.OK, "Trooper created successfully");
        } catch (InvalidTrooperTypeException e) {
            return new Response(HttpStatus.BADREQUEST, "Invalid trooper type: " + type);
        }
    }

    public List<Trooper> getTroopers()
    {
        return trooperService.getTroopers();
    }

    public List<Trooper> getTroopersWithCount(int archerCount,int barbarianCount) throws InsufficientTrooperCountException {
        return trooperService.getTroopersWithCount(archerCount,barbarianCount);
    }
}
