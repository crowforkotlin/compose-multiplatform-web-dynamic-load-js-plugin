import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    js {
        browser {
        }
        binaries.executable()
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedCommon)
        }
    }
}

tasks.named<org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack>(
    "jsBrowserProductionWebpack"
) {
    mainOutputFileName = "plugin.js"
}

