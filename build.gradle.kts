plugins {
    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.13.0-RC2"
}

group = "org.co2dice"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}

dependencies {
    val open_gpt3_version = "0.8.1"
    api("net.mamoe:mirai-console-terminal:2.13.0-RC2") // 自行替换版本
    api("net.mamoe:mirai-core:2.13.0-RC2")

    implementation ("com.theokanning.openai-gpt3-java:client:${open_gpt3_version}")


}
