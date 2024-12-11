package io.ryan.booking.domain.user.application

import io.ryan.booking.domain.user.domain.User
import io.ryan.booking.domain.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserService(
    private val userRepository: UserRepository
) {

    fun get(userId: Long): User {
        val user = userRepository.findByIdOrNull(userId)
        return requireNotNull(user) {
            "유저 정보가 존재하지 않습니다"
        }
    }
}
