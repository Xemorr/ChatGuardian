plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.18.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.7.0")
    implementation("com.microsoft.onnxruntime:onnxruntime:1.20.0")
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.12")
    implementation("ai.djl.huggingface:tokenizers:0.32.0")
    implementation("com.google.inject:guice:7.0.0")
}