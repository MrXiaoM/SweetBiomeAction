rootProject.name = "SweetBiomeAction"

include(":nms")
for (file in File("nms").listFiles() ?: arrayOf()) {
    if (file.isDirectory && File(file, "build.gradle.kts").exists()) {
        include(":nms:${file.name}")
    }
}