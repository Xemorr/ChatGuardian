package me.xemor.chatguardian;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.xemor.chatguardian.command.ChatGuardianCommand;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

import java.nio.file.Path;

public class ChatGuardian extends JavaPlugin implements ChatGuardianCommon {

    private Injector injector;

    @Override
    public void onEnable() {
        Lamp<BukkitCommandActor> lamp = BukkitLamp.builder(this).permissionFactory(new BukkitPermissionFactory()).build();
        injector = Guice.createInjector(
                new PaperModule(
                        this,
                        this.getSLF4JLogger(),
                        this.getServer(),
                        lamp
                )
        );
        ChatGuardianCommand chatGuardianCommand = new ChatGuardianCommand();
        injector.injectMembers(chatGuardianCommand);
        ChatListener chatListener = new ChatListener();
        injector.injectMembers(chatListener);
        this.getServer().getPluginManager().registerEvents(chatListener, this);
        lamp.register(chatGuardianCommand);
    }

    @Override
    public Path dataDirectory() {
        return this.getDataFolder().toPath().toAbsolutePath();
    }
}
