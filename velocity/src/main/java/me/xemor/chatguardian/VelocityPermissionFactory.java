package me.xemor.chatguardian;

import me.xemor.chatguardian.command.RequiresPermission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import revxrsal.commands.Lamp;
import revxrsal.commands.annotation.list.AnnotationList;
import revxrsal.commands.command.CommandPermission;
import revxrsal.commands.velocity.actor.VelocityCommandActor;

public class VelocityPermissionFactory implements CommandPermission.Factory<VelocityCommandActor> {

    @Override
    public @Nullable CommandPermission<VelocityCommandActor> create(@NotNull AnnotationList annotations, @NotNull Lamp<VelocityCommandActor> lamp) {
        RequiresPermission requiresPermission = annotations.get(RequiresPermission.class);
        if (requiresPermission == null) return null;

        String requiredPermission = requiresPermission.value();

        return actor -> actor.source().hasPermission(requiredPermission);
    }
}
