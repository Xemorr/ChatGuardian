package me.xemor.chatguardian.command;

import jakarta.inject.Inject;
import me.xemor.chatguardian.ConfigHandler;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.command.CommandActor;

@Command("chatguardian")
public class ChatGuardianCommand {

    @Inject
    private ConfigHandler configHandler;

    @Subcommand("reload")
    @RequiresPermission("chatguardian.reload")
    public void reload(CommandActor actor) {
        configHandler.reload();
        actor.reply("Reloaded");
    }

}
