package io.ryan.booking.domain.queue.api

import io.ryan.booking.domain.queue.application.TokenFacade
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

@Component
class TokenInterceptor(
    private val tokenFacade: TokenFacade
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val method = handler.method
        val clazz = handler.beanType

        // 클래스 또는 메서드에 @TokenRequired 가 붙어 있는지 확인
        if (!method.isAnnotationPresent(TokenPassRequired::class.java) && !clazz.isAnnotationPresent(TokenPassRequired::class.java)) {
            return true
        }

        val uuid = request.getHeader("Authorization")
        if (uuid.isNullOrBlank()) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return false // 토큰이 유효하지 않으면 요청 차단
        }

        if (!tokenFacade.canPass(UUID.fromString(uuid))) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return false
        }

        return true
    }
}
