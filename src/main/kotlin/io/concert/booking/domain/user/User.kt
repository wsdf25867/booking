package io.concert.booking.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    val name: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {

}
