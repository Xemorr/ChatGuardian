package me.xemor.chatguardian;

import com.google.inject.AbstractModule;

public class CommonModule extends AbstractModule {

    @Override
    public void configure() {
        bind(ConfigHandler.class).asEagerSingleton();
        bind(SentenceEmbeddingModel.class).asEagerSingleton();
    }

}
