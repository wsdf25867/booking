package io.ryan.booking.domain.support.api

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.util.StreamUtils
import java.io.ByteArrayInputStream
import java.io.IOException

class RequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    private val cachedInputStream = StreamUtils.copyToByteArray(request.inputStream)

    override fun getInputStream(): ServletInputStream {
        return object : ServletInputStream() {

            private val cachedBodyInputStream = ByteArrayInputStream(cachedInputStream)

            override fun read(): Int = cachedBodyInputStream.read()

            override fun isFinished(): Boolean =
                try {
                    cachedBodyInputStream.available() == 0
                } catch (e: IOException) {
                    false
                }

            override fun isReady(): Boolean = true

            override fun setReadListener(p0: ReadListener?) =
                throw UnsupportedOperationException()

        }
    }
}