package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.Trooper;

import java.util.List;

public interface InMemoryArmyCampRepository {
    void add(Trooper trooper);

    List<Trooper> getTrainedTroopers();
}
