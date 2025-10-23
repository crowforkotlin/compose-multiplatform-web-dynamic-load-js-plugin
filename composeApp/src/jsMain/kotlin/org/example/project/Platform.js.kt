package org.example.project

// 这是一个外部函数，用于调用由 eval() 放在全局作用域中的 pluginMain
// 我们假设它总是存在。这是 IR 插件将消除的另一个脆弱点。


class JsPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform {
    return JsPlatform()
}



