plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.7.0")
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
}

tasks.test {
    useTestNG()
}