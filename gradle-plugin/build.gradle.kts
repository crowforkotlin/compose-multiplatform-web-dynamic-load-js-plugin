// gradle-plugin/build.gradle.kts
plugins {
    `kotlin-dsl`
}
repositories {
    google {
        mavenContent {
            includeGroupAndSubgroups("androidx")
            includeGroupAndSubgroups("com.android")
            includeGroupAndSubgroups("com.google")
        }
    }
    mavenCentral()
    gradlePluginPortal()
}
group = "org.example.project"
version = "0.0.1"

dependencies {
    // 依赖于我们的编译器插件实现
    implementation(project(":compiler-plugin"))
}

// 将我们的插件暴露给其他模块使用
gradlePlugin {
    plugins {
        create("ziplineServiceGradlePlugin") {
            id = "org.example.project.zipline-service"
            implementationClass = "org.example.project.plugin.ZiplineServiceGradlePlugin"
        }
    }
}