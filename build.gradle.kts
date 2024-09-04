plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.spring)
    alias(libs.plugins.dependency.management)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.spotless)
}

group = "nl.jaysh"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.data.jdbc)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlin.reflect)
    implementation(libs.bundles.coroutines)
    implementation(libs.sqldelight.driver)
    implementation(libs.sqldelight.coroutines.extensions)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.oauth2.client)
    implementation(libs.firebase.admin)
    implementation(libs.bundles.ktor)

    developmentOnly(libs.spring.boot.devtools)

    runtimeOnly(libs.h2)
    runtimeOnly(libs.postgresql)

    testImplementation(libs.bundles.mockk)
    testImplementation(libs.spring.boot.starter.test) {
        exclude(module = "mockito-core")
    }
    testImplementation(libs.spring.security.test)
    testImplementation(libs.kotlin.test.junit5)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    kotlin {
        targetExclude("**/build/**")
        ktfmt("0.46").googleStyle()
    }
}
sqldelight {
    databases {
        create("Database") {
            packageName.set("nl.jaysh.database")
            srcDirs.setFrom("src/main/sqldelight")
            deriveSchemaFromMigrations.set(true)
        }
    }
}
