package io.concert.booking.interfaces.config

import io.concert.booking.application.queue.TokenApplicationService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

@Component
class TokenInterceptor(
    private val tokenApplicationService: TokenApplicationService
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val method = handler.method
        val clazz = handler.beanType

        // 클래스 또는 메서드에 @TokenRequired 가 붙어 있는지 확인
        if (!method.isAnnotationPresent(TokenRequired::class.java) && !clazz.isAnnotationPresent(TokenRequired::class.java)) {
            return true
        }

        val token = request.getHeader("Authorization")
        if (token.isNullOrBlank() || !validateToken(token)) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return false // 토큰이 유효하지 않으면 요청 차단
        }

        return true
    }

    private fun validateToken(token: String): Boolean =
        try {
            tokenApplicationService.validateToken(UUID.fromString(token))
            true
        } catch (e: Exception) {
            false
        }
}
