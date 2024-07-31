package com.VooTreeVeeVuu.usecase.BookingUsecase.UpdateReviewStatus;

import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.domain.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateReviewStatusImpl implements UpdateReviewStatus {
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Optional<Booking> updateReviewStatus(Long bookingId) {
        return bookingRepository.findById(bookingId).map(booking -> {
            booking.setReviewStatus(true);
            return bookingRepository.save(booking);
        });
    }


}
