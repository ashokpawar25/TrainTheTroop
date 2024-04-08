package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.HttpStatus;
import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.repository.Impl.db.impl.exception.InsufficientTrooperCountException;
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

    public Response addTrooperToBarrack(int archerCount,int barbarianCont) {
        try {
            barrackService.addTroopers(archerCount,barbarianCont);
            return new Response(HttpStatus.OK, "Trooper added into barrack");
        } catch (BarrackFullException exception) {
            return new Response(HttpStatus.BADREQUEST, exception.getMessage());
        } catch (InsufficientTrooperCountException e) {
            throw new RuntimeException(e);
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
            return new Response(HttpStatus.BADREQUEST,"Interrupt occurred while training the troopers");
        }

    }
}
