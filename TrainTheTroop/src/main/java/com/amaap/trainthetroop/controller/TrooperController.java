package com.amaap.trainthetroop.controller;
import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.model.TroopType;

public class TrooperController
{
    private TrooperService trooperService;
    public TrooperController(TrooperService trooperService) {
        this.trooperService = trooperService;
    }

    public Response create(TroopType type, int trainingTime, int trainingCost, Weapon weapon)
    {
        boolean isInserted = trooperService.create(type,trainingTime,trainingCost,weapon);
        if(isInserted) return new Response(HttpStatus.OK,"Trooper created successfully");
        return new Response(HttpStatus.BADREQUEST,"Data is invalid");
    }
}
