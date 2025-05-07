plugins {
    java

}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    implementation(project(":common"))
}

tasks.shadowJar {
    minimize()
    relocate("com.fasterxml.jackson", "me.xemor.velocity.jackson")
    configurations = listOf(project.configurations.runtimeClasspath.get())
    destinationDirectory.set(file("F:\\Holder\\Velocity Server\\plugins"))
}