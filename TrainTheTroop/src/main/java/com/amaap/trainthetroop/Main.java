package com.amaap.trainthetroop;

import com.amaap.trainthetroop.controller.ArmyCampController;
import com.amaap.trainthetroop.controller.BarrackController;
import com.amaap.trainthetroop.controller.TrooperController;
import com.amaap.trainthetroop.domain.model.valueobject.TroopType;
import com.amaap.trainthetroop.domain.model.valueobject.Weapon;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.time.Duration;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        TrooperController trooperController = injector.getInstance(TrooperController.class);
        BarrackController barrackController = injector.getInstance(BarrackController.class);

        trooperController.create(TroopType.ARCHER, 6, 20, Weapon.SWORD);
        trooperController.create(TroopType.ARCHER, 6, 20, Weapon.SWORD);
        trooperController.create(TroopType.BARBARIAN, 3, 10, Weapon.BOW_AND_ARROW);
        trooperController.create(TroopType.BARBARIAN, 3, 10, Weapon.BOW_AND_ARROW);
        barrackController.addTrooperToBarrack(2, 2);
        LocalTime trainingStartTime = LocalTime.now();
        barrackController.trainTheTrooper();
        LocalTime trainingEndTime = LocalTime.now();
        long totalTrainingTime = Duration.between(trainingStartTime, trainingEndTime).getSeconds();
        System.out.println("Time taken to train the trooper is " + totalTrainingTime + " sec.");
    }
}
