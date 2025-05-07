package me.xemor.chatguardian;

import com.google.inject.AbstractModule;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

public class VelocityModule extends AbstractModule {

    private final ChatGuardianCommon plugin;
    private final Logger logger;
    private final ProxyServer proxyServer;

    public VelocityModule(ChatGuardianCommon plugin, Logger logger, ProxyServer proxyServer) {
        this.plugin = plugin;
        this.logger = logger;
        this.proxyServer = proxyServer;
    }

    @Override
    protected void configure() {
        bind(ChatGuardianCommon.class).toInstance(plugin);
        bind(Logger.class).toInstance(logger);
        bind(ProxyServer.class).toInstance(proxyServer);
        install(new CommonModule());
    }

}
