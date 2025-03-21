subprojects {
    version = "1.0-SNAPSHOT"
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/") {
            mavenContent {
                includeModule("com.mojang", "datafixerupper")
            }
        }
    }
}

version = "1.0-SNAPSHOT"
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20-R0.1-SNAPSHOT")
    for (proj in subprojects) {
        implementation(proj)
        if (proj.name != "shared") {
            proj.dependencies.implementation(project(":nms:shared"))
            proj.dependencies.compileOnly("com.mojang:datafixerupper:4.1.27")
        }
    }
}
