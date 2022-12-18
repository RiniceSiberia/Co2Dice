plugins {
    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.13.0-RC2"
}

group = "org.co2dice"
version = "0.1.0"

allprojects {
    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

repositories {
//    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}

dependencies {
    val openGpt3Version = "0.8.1"
    val junitVersion = "5.9.1"
    api("net.mamoe:mirai-console-terminal:2.13.0-RC2") // 自行替换版本
    api("net.mamoe:mirai-core:2.13.0-RC2")

    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.theokanning.openai-gpt3-java:api:${openGpt3Version}")
    implementation("com.theokanning.openai-gpt3-java:api:${openGpt3Version}")
    testImplementation("org.junit.jupiter:junit-jupiter:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}
