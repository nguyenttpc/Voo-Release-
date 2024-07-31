package com.VooTreeVeeVuu.usecase.BookingUsecase.GetAllBooking;

import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.domain.repository.BookingRepository;
import com.VooTreeVeeVuu.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllBookingImpl implements GetAllBooking {
    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingDTO> getAllBooking() {
        return bookingRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setHotelOwnerId(booking.getRoom().getHotel().getUser().getId());
        dto.setUserName(booking.getUser().getAccount().getUsername());
        dto.setHotelName(booking.getRoom().getHotel().getHotelName());
        dto.setRoomType(booking.getRoom().getRoomType().getTypeName());
        dto.setAddress(booking.getRoom().getHotel().getAddress());
        dto.setStatus(booking.getStatus());
        dto.setNumOfRoom(booking.getNumOfRoom());
        dto.setNumOfGuest(booking.getNumOfGuest());
        dto.setBookingDate(booking.getBookingDate());
        return dto;
    }
}
