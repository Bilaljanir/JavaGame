plugins {
    id("java")
    id("application")
}

group = "ch.jobtrek.game"
version = "1.0-SNAPSHOT"

application {
    mainClass = "ch.jobtrek.game.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.run.get().standardInput = System.`in`