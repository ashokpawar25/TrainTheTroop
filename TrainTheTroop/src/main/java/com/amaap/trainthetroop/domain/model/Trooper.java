package com.amaap.trainthetroop.domain.model;

import java.util.Objects;

public class Trooper
{
    private final int trainingTime;
    private final int trainingCost;
    private final Weapon weapon;
    public Trooper(int trainingTime, int trainingCost, Weapon weapon) {

        this.trainingTime = trainingTime;
        this.trainingCost = trainingCost;
        this.weapon = weapon;
    }

    public static Trooper create()
    {
        return null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trooper trooper = (Trooper) o;
        return trainingTime == trooper.trainingTime && trainingCost == trooper.trainingCost && weapon == trooper.weapon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingTime, trainingCost, weapon);
    }
}
