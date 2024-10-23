package io.concert.booking.support.web

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class HttpLoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestWrapper = ContentCachingRequestWrapper(request)
        val responseWrapper = ContentCachingResponseWrapper(response)

        loggingRequest(requestWrapper)
        filterChain.doFilter(requestWrapper, responseWrapper)
        loggingResponse(responseWrapper)
    }

    private fun loggingRequest(request: ContentCachingRequestWrapper) {
        log.info("=====HTTP REQUEST=====")
        log.info("${request.method} ${request.requestURI} ${request.protocol}")
        loggingRequestHeaders(request)
        loggingRequestBody(request)
        log.info("=====================")
    }

    private fun loggingRequestBody(request: ContentCachingRequestWrapper) {
        log.info("{}", String(request.contentAsByteArray))
    }


    private fun loggingRequestHeaders(request: ContentCachingRequestWrapper) {
        request.headerNames.toList().forEach {
            log.info("$it: ${request.getHeader(it)}")
        }
    }


    private fun loggingResponse(response: ContentCachingResponseWrapper) {
        log.info("=====HTTP RESPONSE=====")
        log.info("=====HTTP RESPONSE=====")
    }

    companion object {
        private val log = LoggerFactory.getLogger(HttpLoggingFilter::class.java)
    }
}
