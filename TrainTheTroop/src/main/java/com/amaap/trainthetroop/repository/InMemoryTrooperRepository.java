package com.amaap.trainthetroop.repository;

import com.amaap.trainthetroop.domain.model.Trooper;

import java.util.List;

public interface InMemoryTrooperRepository {
    Trooper insert(Trooper trooper) throws Exception;

    List<Trooper> getTroopers();
}
