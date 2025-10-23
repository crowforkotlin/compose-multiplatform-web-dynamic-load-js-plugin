package org.example.project.plugin

import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class ZiplineServiceGradlePlugin : KotlinCompilerPluginSupportPlugin {
    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean {
        // 插件只对 Kotlin/JS 目标生效
        return kotlinCompilation.target.platformType.name == "js"
    }

    override fun getCompilerPluginId(): String = "org.example.project.plugin.zipline-service"

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        groupId = "org.example.project",
        artifactId = "compiler-plugin",
        version = "0.0.1"
    )

    override fun applyToCompilation(
        kotlinCompilation: KotlinCompilation<*>
    ): Provider<List<SubpluginOption>> {
        return kotlinCompilation.target.project.provider { emptyList() }
    }
}