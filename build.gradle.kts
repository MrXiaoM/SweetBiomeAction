import top.mrxiaom.gradle.LibraryHelper

plugins {
    java
    `maven-publish`
    id("com.gradleup.shadow") version "9.3.0"
    id("com.github.gmazzo.buildconfig") version "5.6.7"
}

buildscript {
    repositories.mavenCentral()
    dependencies.classpath("top.mrxiaom:LibrariesResolver-Gradle:1.7.27")
}
val base = LibraryHelper(project)

val targetJavaVersion = 8
val pluginBaseModules = base.modules.run { listOf(library, paper, actions, l10n) }
val shadowGroup = "top.mrxiaom.sweet.biomeaction.libs"
allprojects {
    group = "top.mrxiaom.sweet.biomeaction"
    version = "1.0.0"

    apply(plugin = "java")

    repositories {
        mavenCentral()
        maven("https://repo.codemc.io/repository/maven-public/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        maven("https://jitpack.io")
        maven("https://repo.rosewooddev.io/repository/public/")
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:24.0.0")
    }

    project.extensions.configure<JavaPluginExtension> {
        disableAutoTargetJvm()
        val javaVersion = JavaVersion.toVersion(targetJavaVersion)
        if (JavaVersion.current() < javaVersion) {
            toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
            options.release.set(targetJavaVersion)
        }
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20-R0.1-SNAPSHOT")

    compileOnly("me.clip:placeholderapi:2.12.2")

    base.library(LibraryHelper.adventure("4.25.0"))
    base.collectPluginHolders()

    implementation("com.github.technicallycoded:FoliaLib:0.4.4") { isTransitive = false }
    implementation(base.depend.nbtapi)
    for (artifact in pluginBaseModules) {
        implementation(artifact)
    }
    implementation(base.resolver.lite)

    implementation(project(":nms"))
    implementation(project(":nms:shared"))
}
buildConfig {
    className("BuildConstants")
    packageName("top.mrxiaom.sweet.biomeaction")

    base.doResolveLibraries()
    buildConfigField("String", "VERSION", "\"${project.version}\"")
    buildConfigField("java.time.Instant", "BUILD_TIME", "java.time.Instant.ofEpochSecond(${System.currentTimeMillis() / 1000L}L)")
    buildConfigField("String[]", "RESOLVED_LIBRARIES", base.join())
}

LibraryHelper.initJava(project, base, targetJavaVersion, true)
LibraryHelper.initPublishing(project)

tasks {
    shadowJar {
        configurations.add(project.configurations.runtimeClasspath.get())
        mapOf(
            "top.mrxiaom.pluginbase" to "base",
            "com.tcoded.folialib" to "folialib",
        ).forEach { (original, target) ->
            relocate(original, "$shadowGroup.$target")
        }
    }
}
