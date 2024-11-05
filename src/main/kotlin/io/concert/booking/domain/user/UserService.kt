package io.concert.booking.domain.user

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun get(userId: Long): User {
        val user = userRepository.findById(userId)
        return requireNotNull(user) {
            "유저 정보가 존재하지 않습니다"
        }
    }
}
