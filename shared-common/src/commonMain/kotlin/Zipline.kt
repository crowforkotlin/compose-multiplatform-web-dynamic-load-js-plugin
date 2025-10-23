/**
 * Zipline 运行时实例。
 * 这是一个简化的、非线程安全的版本。
 * 注意：这个 "Pre-IR" 版本依赖于不安全的类型转换和基于字符串的名称。
 */
class Zipline {
    private val services = mutableMapOf<String, Any>()

    fun <T : Any> bind(name: String, instance: T) {
        println("[ZIPLINE] Binding service: '$name'")
        services[name] = instance
    }

    // 这是一个不安全的 take，IR 插件会将其替换
    fun <T : Any> take(name: String): T {
        println("[ZIPLINE] Taking service: '$name'")
        val service = services[name] ?: error("Service not found: '$name'")
        @Suppress("UNCHECKED_CAST")
        return service as T
    }
}