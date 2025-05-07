plugins {
    java
    id("com.gradleup.shadow") version "8.3.6"
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
    implementation(project(":common"))
}

tasks.shadowJar {
    minimize()
    relocate("com.fasterxml.jackson", "me.xemor.paper.jackson")
    configurations = listOf(project.configurations.runtimeClasspath.get())
    destinationDirectory.set(file("build/output/paper"))
}