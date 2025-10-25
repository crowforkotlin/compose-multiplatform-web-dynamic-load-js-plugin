package test.github.zipline.gradle.plugin

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.name.StandardClassIds


/**
 * Zipline辅助类，提前查找缓存所有在代码转换时所需要用到的类、函数、属性、符号
 *
 * time: 2025-10-25 17:46:22 下午 星期六
 * @author: crow
 */
class ZiplineApis(
    val pluginContext: IrPluginContext
) {
    companion object {

        // 如果没有类继承service，就返回null
        fun maybeCreate(pluginContext: IrPluginContext): ZiplineApis? {
            if (pluginContext.referenceClass(ziplineServiceClassId) == null) {
                return null
            }
            return ZiplineApis(pluginContext)
        }


        private val ziplineFqPackage = FqPackageName("app.cash.zipline")
        private val bridgeFqPackage = FqPackageName("app.cash.zipline.internal.bridge")
        private val serializationFqPackage = FqPackageName("kotlinx.serialization")
        private val serializationBuiltInsFqPackage = FqPackageName("kotlinx.serialization.builtins")
        private val collectionsFqPackage = FqPackageName(StandardClassIds.BASE_COLLECTIONS_PACKAGE)
        val contextualClassId = serializationFqPackage.classId("Contextual")
        private val serializationModulesFqPackage = FqPackageName("kotlinx.serialization.modules")
        private val serializersModuleClassId = serializationModulesFqPackage.classId("SerializersModule")
        private val ziplineClassId = ziplineFqPackage.classId("Zipline")
        private val outboundServiceClassId = bridgeFqPackage.classId("OutboundService")
        val ziplineScopedClassId = ziplineFqPackage.classId("ZiplineScoped")
        val ziplineServiceClassId = ziplineFqPackage.classId("ZiplineService")
        private val ziplineServiceSerializerFunctionCallableId = ziplineFqPackage.callableId("ziplineServiceSerializer")
        private val ziplineServiceAdapterFunctionCallableId = bridgeFqPackage.callableId("ziplineServiceAdapter")
        private val ziplineServiceAdapterClassId = bridgeFqPackage.classId("ZiplineServiceAdapter")
        private val endpointClassId = bridgeFqPackage.classId("Endpoint")
        private val suspendCallbackClassId = bridgeFqPackage.classId("SuspendCallback")
        private val flowFqPackage = FqPackageName("kotlinx.coroutines.flow")
        val flowClassId = flowFqPackage.classId("Flow")
        val stateFlowClassId = flowFqPackage.classId("StateFlow")
    }
}