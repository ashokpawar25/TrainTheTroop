package com.amaap.trainthetroop.domain.model.factory;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Barbarian;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;
import com.amaap.trainthetroop.domain.model.exception.InvalidTrooperDataException;
import com.amaap.trainthetroop.service.model.TroopType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TrooperFactory {

    public static Queue<Trooper> getTroopers() throws InvalidTrooperDataException {
        Queue<Trooper> trooperQueue = new LinkedList<>();
        trooperQueue.add(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        trooperQueue.add(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        trooperQueue.add(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        trooperQueue.add(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        trooperQueue.add(new Archer(6, 20, Weapon.BOW_AND_ARROW));
        trooperQueue.add(new Barbarian(3, 10, Weapon.SWORD));
        trooperQueue.add(new Barbarian(3, 10, Weapon.SWORD));
        trooperQueue.add(new Barbarian(3, 10, Weapon.SWORD));
        trooperQueue.add(new Barbarian(3, 10, Weapon.SWORD));
        trooperQueue.add(new Barbarian(3, 10, Weapon.SWORD));
        return trooperQueue;
    }

}
