package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.entity.Trooper;

import java.util.List;

public interface ArmyCampRepository {
    void add(Trooper trooper);

    List<Trooper> getTrainedTroopers();
}
