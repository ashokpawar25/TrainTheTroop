package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.service.BarrackService;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.BarrackFullException;

import java.util.List;
import java.util.Queue;

public class BarrackController {
    private final TrooperService trooperService;
    private final BarrackService barrackService;

    public BarrackController(TrooperService trooperService, BarrackService barrackService) {
        this.trooperService = trooperService;
        this.barrackService = barrackService;
    }

    public Response addTrooperToBarrack(List<Trooper> troopers) {
        try{
            barrackService.addTroopers(troopers);
            return new Response(HttpStatus.OK,"Trooper added into barrack");
        }
        catch (BarrackFullException exception)
        {
            return new Response(HttpStatus.BADREQUEST,exception.getMessage());
        }
    }

    public Queue<Trooper> getTroopers() {
        return null;
    }
}
