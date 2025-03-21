subprojects {
    repositories {
        maven("https://maven.fastmirror.net/repositories/minecraft/") {
            mavenContent {
                includeModule("com.mojang", "datafixerupper")
            }
        }
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20-R0.1-SNAPSHOT")
    for (proj in subprojects) {
        implementation(proj)
        if (proj.name != "shared") {
            proj.dependencies.implementation(project(":nms:shared"))
        }
    }
}
