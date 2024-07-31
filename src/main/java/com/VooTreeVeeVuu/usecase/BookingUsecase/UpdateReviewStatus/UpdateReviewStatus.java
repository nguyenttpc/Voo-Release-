package com.VooTreeVeeVuu.usecase.BookingUsecase.UpdateReviewStatus;

import com.VooTreeVeeVuu.domain.entity.Booking;

import java.util.Optional;

public interface UpdateReviewStatus {
    Optional<Booking> updateReviewStatus(Long bookingId);
}
