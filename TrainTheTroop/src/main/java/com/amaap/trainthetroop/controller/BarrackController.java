package com.amaap.trainthetroop.controller;

import com.amaap.trainthetroop.controller.dto.Response;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.service.BarrackService;
import com.amaap.trainthetroop.service.TrooperService;

import java.util.List;
import java.util.Queue;

public class BarrackController {
    private TrooperService trooperService;
    private BarrackService barrackService;
    public BarrackController(TrooperService trooperService,BarrackService barrackService) {
        this.trooperService = trooperService;
        this.barrackService = barrackService;
    }

    public void addTrooperToBarrack(List<Trooper> troopers) {
        List<Trooper> trooperList = trooperService.getTroopers();
        barrackService.addTroopers(trooperList);
    }

    public Queue<Trooper> getTroopers() {
        return null;
    }
}
