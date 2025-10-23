package org.example.project

import HostGreeterService
import PluginReporterService
import Zipline

// 插件提供的服务实现
class PluginReporterServiceImpl : PluginReporterService {
    override fun report(message: String) {
        console.log("[PLUGIN_SERVICE] REPORT: $message")
    }
}

// 这是插件的入口点，必须用 @JsExport 暴露给 JS 世界
@JsExport
fun pluginMain(zipline: Zipline) {
    console.log("[PLUGIN] pluginMain has started.")

    // 1. 从宿主获取服务
    val greeter = zipline.take<HostGreeterService>("HostGreeter")
    val hostMessage = greeter.greet("Plugin")
    console.log("[PLUGIN] Message from host: $hostMessage")

    // 2. 绑定自己的服务供宿主使用
    zipline.bind<PluginReporterService>("PluginReporter", PluginReporterServiceImpl())

    console.log("[PLUGIN] pluginMain finished.")
}