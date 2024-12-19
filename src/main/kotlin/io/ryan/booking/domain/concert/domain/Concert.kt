package io.ryan.booking.domain.concert.domain

import io.ryan.booking.domain.support.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "concerts")
class Concert(
    val name: String,
    val artistName: String,
    val place: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
): BaseEntity<Concert>()
