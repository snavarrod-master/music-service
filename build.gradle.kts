import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "com.usj"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.22")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.mchange:c3p0:0.9.5.5")
    implementation("com.h2database:h2:2.1.212")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.hibernate:hibernate-core:6.1.7.Final")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    implementation("com.github.joschi.jackson:jackson-datatype-threetenbp:2.12.5")
    implementation("org.springframework.boot:spring-boot-starter:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-web-services:3.0.4")
    implementation("org.springframework.security:spring-security-test:6.0.2")
    implementation("org.springframework.data:spring-data-jpa:3.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("org.web3j:core:4.9.7")
    implementation("org.webjars.npm:openapi-contrib__openapi-schema-to-json-schema:3.2.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools:3.0.4")
    testImplementation(platform("org.junit:junit-bom:5.7.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
