package me.xemor.chatguardian;

import com.google.inject.AbstractModule;
import org.bukkit.Server;
import org.slf4j.Logger;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

public class PaperModule extends AbstractModule {

    private final ChatGuardianCommon plugin;
    private final Logger logger;
    private final Server server;
    private final Lamp<BukkitCommandActor> lamp;

    public PaperModule(ChatGuardianCommon plugin, Logger logger, Server server, Lamp<BukkitCommandActor> lamp) {
        this.plugin = plugin;
        this.logger = logger;
        this.server = server;
        this.lamp = lamp;
    }

    @Override
    protected void configure() {
        bind(ChatGuardianCommon.class).toInstance(plugin);
        bind(Logger.class).toInstance(logger);
        bind(Server.class).toInstance(server);
        bind(Lamp.class).toInstance(lamp);
        install(new CommonModule());
    }

}
