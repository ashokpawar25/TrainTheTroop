package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.domain.model.entity.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.InvalidTrooperTypeException;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;

import java.util.List;

public class TrooperController {
    private final TrooperService trooperService;

    public TrooperController(TrooperService trooperService) {
        this.trooperService = trooperService;
    }

    public Response create(TroopType type, int trainingTime, int trainingCost, Weapon weapon) {
        try {
            trooperService.create(type, trainingTime, trainingCost, weapon);
            return new Response(HttpStatus.OK, "Trooper created successfully");
        } catch (InvalidTrooperTypeException | InvalidTrooperDataException exception) {
            return new Response(HttpStatus.BADREQUEST, exception.getMessage());
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
