package io.concert.booking.interfaces.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val tokenInterceptor: TokenInterceptor
) : WebMvcConfigurer {



    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenInterceptor)
            .addPathPatterns("/**")
    }
}