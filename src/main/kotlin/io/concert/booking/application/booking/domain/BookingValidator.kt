package io.concert.booking.application.booking.domain

interface BookingValidator {
    fun validate(booking: Booking)
}