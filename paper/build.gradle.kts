plugins {
    java
    id("com.gradleup.shadow") version "8.3.6"
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.12")
    implementation("io.github.revxrsal:lamp.bukkit:4.0.0-rc.12")
    implementation("com.google.inject:guice:7.0.0")
    implementation(project(":common"))
}

tasks.shadowJar {
    relocate("com.fasterxml.jackson", "me.xemor.chatguardian.jackson")
    relocate("io.github.revxrsal", "me.xemor.chatguardian.io.github.revxrsal")
    relocate("com.google.inject", "me.xemor.chatguardian.guice")
    configurations = listOf(project.configurations.runtimeClasspath.get())
    val folder = System.getenv("pluginFolder")
    destinationDirectory.set(file(folder))
}

tasks.processResources {
    inputs.property("version", rootProject.version)
    expand("version" to rootProject.version)
}