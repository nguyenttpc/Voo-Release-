package com.VooTreeVeeVuu.usecase.BookingUsecase.GetBooking;

import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.domain.repository.BookingRepository;
import com.VooTreeVeeVuu.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetBookingImpl implements GetBooking {
    @Autowired
    private BookingRepository bookingRepository;

    public Optional<BookingDTO> getBookingById(Long id) {
        return bookingRepository.findById(id).map(this::toDTO);
    }

    private BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setStatus(booking.getStatus());
        dto.setNumOfRoom(booking.getNumOfRoom());
        dto.setNumOfGuest(booking.getNumOfGuest());
        dto.setCity(booking.getRoom().getHotel().getCity());
        return dto;
    }
}
