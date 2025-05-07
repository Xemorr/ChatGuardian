package me.xemor.chatguardian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.Injector;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.File;
import java.io.IOException;

@Singleton
public class ConfigHandler {

    @Inject
    private ChatGuardianCommon chatGuardianCommon;
    @Inject
    private Injector injector;
    private Classifier classifier;

    @Inject
    public ConfigHandler(ChatGuardianCommon chatGuardianCommon, Injector injector) {
        this.chatGuardianCommon = chatGuardianCommon;
        this.injector = injector;
        reload();
    }

    public void reload() {
        try {
            classifier = new ObjectMapper(new YAMLFactory()).readValue(new File(chatGuardianCommon.dataDirectory().toFile(), "filters.yml"), Classifier.class);
            injector.injectMembers(classifier);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Classifier getClassifier() {
        return classifier;
    }
}
