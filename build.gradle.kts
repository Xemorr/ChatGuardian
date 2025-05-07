import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    base // gives you 'clean' task etc.
    id("com.gradleup.shadow") version "8.3.6"
}

allprojects {
    group = "me.xemor"
    version = "1.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/") {
            name = "papermc-repo"
        }
        maven("https://oss.sonatype.org/content/groups/public/") {
            name = "sonatype"
        }
    }
}

subprojects {
    apply(plugin = "com.gradleup.shadow")
    tasks.withType<ShadowJar>().configureEach {
        archiveBaseName.set("${rootProject.name}-${project.name}")
    }
}