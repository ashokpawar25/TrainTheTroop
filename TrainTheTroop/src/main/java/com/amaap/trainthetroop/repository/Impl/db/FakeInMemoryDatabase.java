package com.amaap.trainthetroop.repository.Impl.db;

import com.amaap.trainthetroop.domain.model.Archer;
import com.amaap.trainthetroop.domain.model.Trooper;
import com.amaap.trainthetroop.domain.model.Weapon;

import java.util.ArrayList;
import java.util.List;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    List<Trooper> trooperList = new ArrayList<>();
    @Override
    public boolean insertIntoTrooperTable(int trainingTime, int trainingCost, Weapon weapon) {
        Trooper trooper;
        try {
            trooper = new Archer(trainingTime,trainingCost,weapon);
        }
        catch (Exception e)
        {
            return false;
        }
        trooperList.add(trooper);
        return true;
    }
}
