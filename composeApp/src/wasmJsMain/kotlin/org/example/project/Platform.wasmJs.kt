package org.example.project

import Zipline

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"


}

actual fun getPlatform(): Platform = WasmPlatform()