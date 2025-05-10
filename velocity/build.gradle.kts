plugins {
    java

}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    implementation("io.github.revxrsal:lamp.velocity:4.0.0-rc.12")
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.12")
    implementation("io.github.revxrsal:lamp.brigadier:4.0.0-rc.12")
    implementation(project(":common"))
}

tasks.shadowJar {
    minimize()
    relocate("com.fasterxml.jackson", "me.xemor.chatguardian.jackson")
    relocate("io.github.revxrsal", "me.xemor.chatguardian.io.github.revxrsal")
    configurations = listOf(project.configurations.runtimeClasspath.get())
    destinationDirectory.set(file("F:\\Holder\\Velocity Server\\plugins"))
}

tasks.processResources {
    inputs.property("version", rootProject.version)
    expand("version" to rootProject.version)
}