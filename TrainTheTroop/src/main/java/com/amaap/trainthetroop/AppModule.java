package com.amaap.trainthetroop;

import com.amaap.trainthetroop.repository.ArmyCampRepository;
import com.amaap.trainthetroop.repository.BarrackRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryArmyCampRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryBarrackRepository;
import com.amaap.trainthetroop.repository.Impl.InMemoryTrooperRepository;
import com.amaap.trainthetroop.repository.TrooperRepository;
import com.amaap.trainthetroop.repository.db.InMemoryDatabase;
import com.amaap.trainthetroop.repository.db.impl.FakeInMemoryDatabase;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure()
    {
        bind(TrooperRepository.class).to(InMemoryTrooperRepository.class);
        bind(BarrackRepository.class).to(InMemoryBarrackRepository.class);
        bind(ArmyCampRepository.class).to(InMemoryArmyCampRepository.class);
        bind(InMemoryDatabase.class).to(FakeInMemoryDatabase.class).asEagerSingleton();
    }
}
