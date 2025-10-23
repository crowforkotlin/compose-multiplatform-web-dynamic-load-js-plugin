// 由宿主实现，供插件调用
interface HostGreeterService {
    fun greet(from: String): String
}

// 由插件实现，供宿主调用
interface PluginReporterService {
    fun report(message: String)
}