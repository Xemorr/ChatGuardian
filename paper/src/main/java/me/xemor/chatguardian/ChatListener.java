package me.xemor.chatguardian;

import io.papermc.paper.event.player.AsyncChatEvent;
import jakarta.inject.Inject;
import me.xemor.chatguardian.chatlistener.GenericChatListener;
import net.kyori.adventure.audience.Audiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @Inject
    private GenericChatListener genericChatListener;

    @EventHandler
    public void onChat(AsyncChatEvent e) {
        String messageToClassify = MiniMessage.builder().build().serialize(e.message());
        genericChatListener.listen(
                e.getPlayer().getName(),
                messageToClassify,
                (messageToStaff) -> {
                    Bukkit.getOnlinePlayers().stream()
                            .filter(this::isStaffMember)
                            .forEach((player) -> player.sendMessage(MiniMessage.miniMessage().deserialize(messageToStaff))
                    );
                },
                "this-server"
        );
    }

    public boolean isStaffMember(Player player) {
        return (
                player.hasPermission("chatguardian.notify")
                || player.hasPermission("chatguardian.alwaysnotify"));
    }

}
