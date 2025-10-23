import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js

class ZiplineLoader {
    suspend fun load(pluginUrl: String, initializerBlock: (Zipline) -> Unit): Zipline {
        println("[LOADER] Starting to load plugin from: $pluginUrl")

        val zipline = Zipline()

        println("[LOADER] Executing host initializer block...")
        initializerBlock(zipline)
        println("[LOADER] Host initializer block finished.")

        try {
            val httpClient = HttpClient {

            }
            val pluginCode = httpClient.get(pluginUrl) {
            }.bodyAsText()
            println("[LOADER] Executing downloaded plugin code via safeEvalInGlobalContext...")
            safeEvalInGlobalContext(pluginCode)
            println("[LOADER] Plugin code executed safely.")

            // =======================================================================
            //  CORE FIX: Use the exact path discovered from the console.log
            // =======================================================================
            println("[LOADER] Calling plugin.org.example.project.pluginMain on the window object...")

            // The path directly matches the package name structure.
            js("window.plugin.org.example.project.pluginMain(zipline)")

            println("[LOADER] plugin.pluginMain finished.")

            return zipline

        } catch (e: dynamic) {
            println("[LOADER] Failed to load or execute plugin: $e")
        }
        return zipline
    }
}

external fun pluginMain(zipline: Zipline)

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun("function(code) { return new Function(code).call(window); }")
external fun safeEvalInGlobalContext(code: String)