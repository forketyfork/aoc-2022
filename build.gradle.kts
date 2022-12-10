import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0-RC"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.1")
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks {

    wrapper {
        gradleVersion = "7.6"
    }

    test {
        useJUnitPlatform()
        afterTest(KotlinClosure2<TestDescriptor, TestResult, Any>({ descriptor, result ->
            val test = descriptor as org.gradle.api.internal.tasks.testing.TestDescriptorInternal
            println("${test.className} > ${test.name} [${test.displayName}]: ${result.resultType}")
        }))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.languageVersion = "1.8"
    kotlinOptions.jvmTarget = "19"
}