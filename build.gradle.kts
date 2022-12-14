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
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    maven("https://libraries.minecraft.net") // mc的库
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")

    mavenCentral()
}

dependencies {
    val miraiVersion = "2.13.0-RC2"
    val openGpt3Version = "0.8.1"
    val junitVersion = "5.9.1"
    val dataFixerUpperVersion = "4.1.27"
    val log4jVersion = "2.19.0"
    val gsonVersion = "2.10"
    api("net.mamoe:mirai-console-terminal:${miraiVersion}") // 自行替换版本
    api("net.mamoe:mirai-core:${miraiVersion}")

    implementation("com.google.code.gson:gson:${gsonVersion}")
    implementation("org.apache.logging.log4j:log4j-api:${log4jVersion}")
    implementation("com.theokanning.openai-gpt3-java:api:${openGpt3Version}")
    implementation("com.google.code.gson:gson:2.8.7")
    testImplementation("org.junit.jupiter:junit-jupiter:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    implementation("com.mojang:datafixerupper:${dataFixerUpperVersion}")
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}
