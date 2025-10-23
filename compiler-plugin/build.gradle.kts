// compiler-plugin/build.gradle.kts
plugins {
    id("org.jetbrains.kotlin.jvm")
}

group = "org.example.project"
version = "0.0.1"

dependencies {
    // 核心依赖：Kotlin 编译器 API
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.2.20")
}