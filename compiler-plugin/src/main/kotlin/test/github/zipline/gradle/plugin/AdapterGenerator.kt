package test.github.zipline.gradle.plugin

import org.jetbrains.kotlin.backend.common.ScopeWithIr
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.types.IrTypeSystemContextImpl

class AdapterGenerator(
    private val pluginContext: IrPluginContext,
    private val ziplineApis: ZiplineApis,
    private val scope: ScopeWithIr,
    private val originalDeclaration: IrClass
) {
    private val irFactory = pluginContext.irFactory
    private val irTypeSystemContext = IrTypeSystemContextImpl(pluginContext.irBuiltIns)

    fun generateAdapterIfAbsent() {

        val companion = getOrCreateCompanion(originalDeclaration, pluginContext)
        return getOrCreateAdapterClass(companion)
    }
}