package me.xemor.chatguardian;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.xemor.chatguardian.chatlistener.GenericChatListener;

public class CommonModule extends AbstractModule {

    @Override
    public void configure() {
        bind(ConfigHandler.class).in(Singleton.class);;
        bind(SentenceEmbeddingModel.class).in(Singleton.class);;
        bind(GenericChatListener.class).in(Singleton.class);;
    }

}
