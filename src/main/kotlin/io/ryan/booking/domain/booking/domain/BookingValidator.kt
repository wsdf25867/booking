package io.ryan.booking.domain.booking.domain

interface BookingValidator {
    fun validate(booking: Booking)
}