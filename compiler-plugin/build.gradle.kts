// compiler-plugin/build.gradle.kts
plugins {
    id("org.jetbrains.kotlin.jvm") version "2.2.20"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.google.devtools.ksp") version "2.2.20-2.0.4"
}

dependencies {
    implementation(kotlin("gradle-plugin-api"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.20")
    compileOnly(kotlin("compiler-embeddable"))
    compileOnly(kotlin("stdlib"))
    ksp("dev.zacsweers.autoservice:auto-service-ksp:1.2.0")
    compileOnly("com.google.auto.service:auto-service-annotations:1.1.1")
}

gradlePlugin {

    plugins {
        create("zipline") {
            id = "test2.github.zipline"
            version = "1.0.0"
            displayName = "zipline"
            description = "Compiler plugin to generate bridges between platforms"
            implementationClass = "test2.github.zipline.gradle.plugin.ZiplinePlugin"
        }
    }
}

group = "test2.github.zipline"
version = "1.0.0" // 您的版本號

val sourcesJar by tasks.registering(Jar::class) {
    //如果没有配置main会报错
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

publishing {
    //配置maven仓库
    repositories {
        maven {
            //当前项目根目录
            url = uri("$rootDir/repo")
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "test2.github"
            artifactId = "zipline"
            version = "1.0.0"
        }

    }
}