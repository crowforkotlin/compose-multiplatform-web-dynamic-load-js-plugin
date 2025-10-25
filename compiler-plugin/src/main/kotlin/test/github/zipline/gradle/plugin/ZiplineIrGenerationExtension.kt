package test.github.zipline.gradle.plugin

import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.util.classId
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.isInterface

class ZiplineIrGenerationExtension(
    private val messageCollector: MessageCollector
) : IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {

        // 创建zipline context 如果没有关联的类，退出函数，不处理IR
        val ziplineApis = ZiplineApis.maybeCreate(pluginContext) ?: return

        val transformer = object : IrElementTransformerVoidWithContext() {

            override fun visitClassNew(declaration: IrClass): IrStatement {
                val declaration = super.visitClassNew(declaration) as IrClass
                try {
                    if (declaration.isInterface && declaration.superTypes.any { it.getClass()?.classId == ZiplineApis.ziplineScopedClassId } ) {
                        AdapterGenerator(
                            pluginContext = pluginContext,
                            ziplineApis = ziplineApis,
                            scope = currentScope!!,
                            originalDeclaration = declaration,
                        ).generateAdapterIfAbsent()
                    }
                } catch (exception: Exception) {
                    throw Exception(
                        "Only classes may implement ZiplineScoped, but" +
                                " ${declaration.fqNameWhenAvailable} is an interface" + "\t ${exception.message}",
                    )
                }
                return declaration
            }

            override fun visitCall(expression: IrCall): IrExpression {
                val expression = super.visitCall(expression) as IrCall
                return expression
            }

        }


    }
}