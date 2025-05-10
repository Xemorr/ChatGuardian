package me.xemor.chatguardian;

import me.xemor.chatguardian.command.RequiresPermission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import revxrsal.commands.Lamp;
import revxrsal.commands.annotation.list.AnnotationList;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.command.CommandPermission;

public class BukkitPermissionFactory implements CommandPermission.Factory<BukkitCommandActor> {

    @Override
    public @Nullable CommandPermission<BukkitCommandActor> create(@NotNull AnnotationList annotations, @NotNull Lamp<BukkitCommandActor> lamp) {
        RequiresPermission requiresPermission = annotations.get(RequiresPermission.class);
        if (requiresPermission == null) return null;

        String requiredPermission = requiresPermission.value();

        return actor -> actor.sender().hasPermission(requiredPermission);
    }
}
