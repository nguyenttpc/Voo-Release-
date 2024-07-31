package com.VooTreeVeeVuu.usecase.BookingUsecase.GetBooking;

import com.VooTreeVeeVuu.dto.BookingDTO;

import java.util.Optional;

public interface GetBooking {
	Optional<BookingDTO> getBookingById(Long id);
}
