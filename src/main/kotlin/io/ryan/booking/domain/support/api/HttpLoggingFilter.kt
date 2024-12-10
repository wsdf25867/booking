package io.ryan.booking.domain.support.api

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class HttpLoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestWrapper = RequestWrapper(request)
        val responseWrapper = ContentCachingResponseWrapper(response)

        loggingRequest(requestWrapper)

        filterChain.doFilter(requestWrapper, responseWrapper)

        loggingResponse(responseWrapper)
        responseWrapper.copyBodyToResponse()
    }

    private fun loggingRequest(request: RequestWrapper) {
        log.info("======HTTP REQUEST======")
        log.info("${request.method} ${request.requestURI} ${request.protocol}")
        loggingRequestHeaders(request)
        loggingRequestBody(request)
        log.info("=======================")
    }

    private fun loggingRequestBody(request: RequestWrapper) {
        String(request.inputStream.readAllBytes())
            .lines()
            .forEach(log::info)
    }


    private fun loggingRequestHeaders(request: RequestWrapper) {
        request.headerNames.toList().forEach {
            log.info("$it: ${request.getHeader(it)}")
        }
    }


    private fun loggingResponse(response: ContentCachingResponseWrapper) {
        log.info("=====HTTP RESPONSE=====")
        log.info("status: ${response.status}")
        loggingResponseHeaders(response)
        loggingResponseBody(response)
        log.info("=======================")
    }

    private fun loggingResponseBody(response: ContentCachingResponseWrapper) {
        String(response.contentAsByteArray)
            .lines()
            .forEach(log::info)
    }

    private fun loggingResponseHeaders(response: ContentCachingResponseWrapper) {
        response.headerNames.toList().forEach {
            log.info("$it: ${response.getHeader(it)}")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(HttpLoggingFilter::class.java)
    }
}
