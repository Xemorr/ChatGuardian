package me.xemor.chatguardian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.Injector;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

@Singleton
public class ConfigHandler {

    private final ChatGuardianCommon chatGuardianCommon;
    private final Injector injector;
    private Classifier classifier;

    @Inject
    public ConfigHandler(ChatGuardianCommon chatGuardianCommon, Injector injector) {
        this.chatGuardianCommon = chatGuardianCommon;
        this.injector = injector;
        try {
            extractResourcesIfEmpty("/default_files", chatGuardianCommon.dataDirectory());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
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

    public void extractResourcesIfEmpty(String resourcePath, Path destinationDir) throws IOException, URISyntaxException {
        if (!Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);
        }

        Stream<Path> list = Files.list(destinationDir);
        if (!Files.isDirectory(destinationDir) || list.findAny().isPresent()) {
            // Destination exists and is not empty
            return;
        }
        list.close();

        URL resourceUrl = this.getClass().getResource(resourcePath);
        if (resourceUrl == null) {
            throw new FileNotFoundException("Resource path not found: " + resourcePath);
        }

        if (resourceUrl.getProtocol().equals("jar")) {
            // Running from a JAR
            String jarPath = resourceUrl.getPath().substring(5, resourceUrl.getPath().indexOf("!"));
            try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8))) {
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.contains("plugin")) continue;

                    if (name.startsWith(resourcePath.substring(1))) { // strip leading slash
                        Path filePath = destinationDir.resolve(name.substring(resourcePath.length()));
                        if (entry.isDirectory()) {
                            Files.createDirectories(filePath);
                        } else {
                            Files.createDirectories(filePath.getParent());
                            try (InputStream in = jar.getInputStream(entry)) {
                                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
                            }
                        }
                    }
                }
            }
        } else if (resourceUrl.getProtocol().equals("file")) {
            // Running from file system (e.g., during development)
            Path sourceDir = Paths.get(resourceUrl.toURI());
            Files.walk(sourceDir).forEach(sourcePath -> {
                try {
                    Path relative = sourceDir.relativize(sourcePath);
                    Path targetPath = destinationDir.resolve(relative);
                    if (Files.isDirectory(sourcePath)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } else {
            throw new UnsupportedOperationException("Unsupported resource protocol: " + resourceUrl.getProtocol());
        }
    }
}
