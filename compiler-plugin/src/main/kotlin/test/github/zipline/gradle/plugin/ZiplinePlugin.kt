package test2.github.zipline.gradle.plugin

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

@Suppress("unused") // Created reflectively by Gradle.
class ZiplinePlugin : KotlinCompilerPluginSupportPlugin {
    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        return kotlinCompilation.target.project.provider { listOf() }
    }


    override fun getCompilerPluginId(): String { return "org.example.project.kotlin" }

    override fun getPluginArtifact(): SubpluginArtifact {
        return SubpluginArtifact(
            groupId = "test2.github.zipline",
            artifactId = "compiler-plugin",
            version = "1.0.0"
        )
    }

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean { return true }


    override fun apply(target: Project) {
        super.apply(target)

    }
}