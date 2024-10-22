package io.concert.booking.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    val name: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {

}
