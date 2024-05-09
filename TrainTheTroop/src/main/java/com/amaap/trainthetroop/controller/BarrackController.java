package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.entity.Trooper;
import com.amaap.trainthetroop.repository.db.impl.exception.InsufficientTrooperCountException;
import com.amaap.trainthetroop.service.BarrackService;
import com.amaap.trainthetroop.service.TrooperService;
import com.amaap.trainthetroop.service.exception.BarrackSizeFullException;

import java.util.Queue;

public class BarrackController {
    private final BarrackService barrackService;

    public BarrackController( BarrackService barrackService) {
        this.barrackService = barrackService;
    }

    public Response addTrooperToBarrack(int archerCount,int barbarianCount) {
        try {
            barrackService.addTroopers(archerCount,barbarianCount);
            return new Response(HttpStatus.OK, "Trooper added into barrack");
        } catch (InsufficientTrooperCountException | BarrackSizeFullException e) {
            return new Response(HttpStatus.BADREQUEST, e.getMessage());
        }
    }

    public Queue<Trooper> getTroopers() {
        return barrackService.getTroopersFromBarrack();
    }

    public Response trainTheTrooper() {
        try {
            barrackService.trainTheTrooper();
            return new Response(HttpStatus.OK,"Trooper trained successfully");
        }
        catch (InterruptedException exception)
        {
            return new Response(HttpStatus.BADREQUEST,exception.getMessage());
        }

    }
}
