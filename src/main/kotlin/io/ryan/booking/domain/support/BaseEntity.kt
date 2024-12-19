package io.ryan.booking.domain.support

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AbstractAggregateRoot
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity<T: BaseEntity<T>> (
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now(),
): AbstractAggregateRoot<T>() {
}
