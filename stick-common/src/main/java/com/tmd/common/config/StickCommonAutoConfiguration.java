package com.tmd.common.config;

//import com.tmd.common.filter.JwtAuthenticationTokenFilter;
import com.tmd.common.properties.AliOssProperties;
import com.tmd.common.properties.MailProperties;
import com.tmd.common.properties.WechatPayProperties;
import com.tmd.common.repository.InMemoryChatHistoryRespository;
import com.tmd.common.repository.LocalPdfFileRepository;
import com.tmd.common.util.JwtUtil;
import com.tmd.common.util.NeedTools;
import com.tmd.common.util.RedisIdWorker;
import com.tmd.common.util.SimpleTools;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Stick Common 模块自动配置类
 * 显式导入所有需要自动装配的配置类和组件，避免使用全包扫描带来的性能问题和不可控性。
 */
@Configuration
@Import({
        // === 配置类 ===
        // === 配置类 ===
        // CacheRedisCaffeineAutoConfiguration.class,
        CommonConfiguration.class,
        CorsConfig.class,
        CustomConfiguration.class,
        ESConfig.class,
        L2CacheConfig.class,
        MvcConfiguration.class,
        PerformanceConfig.class,
        PostsIndexInitializer.class,
        RabbitMQConfig.class,
        RedisConfig.class,
        RetryConfig.class,
        // SecurityConfig.class, // 移除SecurityConfig自动配置，交由各服务自行配置
        ThreadPoolConfig.class,
        WebSocketConfig.class,

        // === 核心组件 ===
        RedisCache.class, // 位于config包下但实际是工具组件
        // JwtAuthenticationTokenFilter.class, // 移除过滤器自动配置，交由各服务自行配置

        // === 属性配置 === 属性配置 ===
        AliOssProperties.class,
        MailProperties.class,
        WechatPayProperties.class,

        // === 仓储组件 ===
        InMemoryChatHistoryRespository.class,
        LocalPdfFileRepository.class,

        // === 工具组件 ===
        JwtUtil.class,
        NeedTools.class,
        RedisIdWorker.class,
        SimpleTools.class
})
public class StickCommonAutoConfiguration {
}
