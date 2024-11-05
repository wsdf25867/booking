package io.concert.booking.domain.user

import org.springframework.stereotype.Component

@Component
class UserService(
    private val userRepository: UserRepository
) {

    fun get(userId: Long): User {
        val user = userRepository.findById(userId)
        return requireNotNull(user) {
            "유저 정보가 존재하지 않습니다"
        }
    }
}
