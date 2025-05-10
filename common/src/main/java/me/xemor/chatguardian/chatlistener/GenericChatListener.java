package me.xemor.chatguardian.chatlistener;

import jakarta.inject.Inject;
import me.xemor.chatguardian.ChatGuardianCommon;
import me.xemor.chatguardian.Classifier;
import me.xemor.chatguardian.ConfigHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class GenericChatListener {

    @Inject
    private ChatGuardianCommon chatGuardianCommon;
    @Inject
    private ConfigHandler configHandler;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public boolean listen(String playerName, String message, Consumer<String> sendMessageToAllStaff, String serverName) {
        Classifier classifier = configHandler.getClassifier();
        Optional<String> classify = classifier.classify(message);
        if (classify.isPresent()) {
            sendMessageToAllStaff.accept(
                    "<red>%s <gray>in <red>%s <gray>sent <red>\"%s\" <gray>similar to <red>'%s'".formatted(
                            playerName,
                            serverName,
                            message,
                            classify.get()
                    )
            );
        }
        try {
            reentrantLock.lock();
            File serverFile = new File(chatGuardianCommon.dataDirectory().toFile(), serverName + "-messages.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(serverFile, true))) {
                writer.newLine(); // Adds a new line before appending
                writer.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        finally {
            reentrantLock.unlock();
        }
        return classify.isPresent();
    }

}
