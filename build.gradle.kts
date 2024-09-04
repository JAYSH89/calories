plugins {
  alias(libs.plugins.jvm)
  alias(libs.plugins.kotlin.spring)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.spring)
  alias(libs.plugins.dependency.management)
  alias(libs.plugins.spotless)
  jacoco
}

group = "nl.jaysh"

version = "0.0.1"

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

repositories { mavenCentral() }

dependencies {
  implementation(libs.bundles.coroutines)
  implementation(libs.bundles.exposed)
  implementation(libs.bundles.ktor)
  implementation(libs.firebase.admin)
  implementation(libs.kotlin.reflect)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.spring.boot.starter.data.jdbc)
  implementation(libs.spring.boot.starter.oauth2.client)
  implementation(libs.spring.boot.starter.security)
  implementation(libs.spring.boot.starter.web)

  developmentOnly(libs.spring.boot.devtools)

  runtimeOnly(libs.h2)
  runtimeOnly(libs.postgresql)

  testImplementation(libs.bundles.mockk)
  testImplementation(libs.kotlin.test.junit5)
  testImplementation(libs.spring.boot.starter.test) { exclude(module = "mockito-core") }
  testImplementation(libs.spring.security.test)

  testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.test {
  useJUnitPlatform()
  finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
  dependsOn(tasks.test)

  reports {
    xml.required = true
    csv.required = true
    html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
  }
}

jacoco { toolVersion = "0.8.12" }

spotless {
  kotlin {
    targetExclude("**/build/**")
    ktfmt("0.52").googleStyle()
  }
}
