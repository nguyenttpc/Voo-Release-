package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.domain.entity.Room;
import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.repository.BookingRepository;
import com.VooTreeVeeVuu.domain.repository.RoomRepository;
import com.VooTreeVeeVuu.domain.repository.UserRepository;
import com.VooTreeVeeVuu.domain.utils.Booking_status;
import com.VooTreeVeeVuu.dto.BookingDTO;
import com.VooTreeVeeVuu.dto.InsertBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;


    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public InsertBookingDTO createBooking(InsertBookingDTO bookingDTO) {
        User customer = userRepository.findById(bookingDTO.getUserId()).orElseThrow(() -> new RuntimeException("Customer not found"));

        Room room = roomRepository.findById(bookingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

        Booking booking = new Booking();
        booking.setUser(customer);
        booking.setRoom(room);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus(Booking_status.PENDING);
        booking.setCheckInDate(bookingDTO.getCheck_in_date());
        booking.setCheckOutDate(bookingDTO.getCheck_out_date());
        booking.setNumOfGuest(bookingDTO.getNum_of_guest());
        booking.setNumOfRoom(bookingDTO.getNum_of_rooms());
        booking.setTotalPrice(bookingDTO.getTotal_price());
        booking.setReviewStatus(false);
        Booking saved = bookingRepository.save(booking);

        scheduler.schedule(() -> {
            deleteBookingIfPending(saved.getId());
        }, 45, TimeUnit.MINUTES);

        return mapToDTO(saved);
    }

    public List<BookingDTO> getUserBookingHistory(Long userId) {
        return bookingRepository.findBookingHistoryByUser(userId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    private BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setUserName(booking.getUser().getAccount().getUsername());
        dto.setHotelName(booking.getRoom().getHotel().getHotelName());
        dto.setRoomType(booking.getRoom().getRoomType().getTypeName());
        dto.setAddress(booking.getRoom().getHotel().getAddress());
        dto.setStatus(booking.getStatus());
        dto.setHotelId(booking.getRoom().getHotel().getId());
        dto.setCity(booking.getRoom().getHotel().getCity());
        dto.setNumOfRoom(booking.getNumOfRoom());
        dto.setNumOfGuest(booking.getNumOfGuest());
        dto.setBookingDate(booking.getBookingDate());
        dto.setReviewStatus(booking.isReviewStatus());
        return dto;
    }

    private InsertBookingDTO mapToDTO(Booking booking) {
        InsertBookingDTO dto = new InsertBookingDTO();
        dto.setId(booking.getId());
        dto.setCheck_in_date(booking.getCheckInDate());
        dto.setCheck_out_date(booking.getCheckOutDate());
        dto.setTotal_price(booking.getTotalPrice());
        dto.setUserId(booking.getUser().getId());
        dto.setRoomId(booking.getRoom().getId());
        dto.setStatus(booking.getStatus());
        dto.setNum_of_rooms(booking.getNumOfRoom());
        dto.setNum_of_guest(booking.getNumOfGuest());
        dto.setBookingDate(booking.getBookingDate());
        return dto;
    }

    @Transactional
    public void deleteBookingIfPending(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null && booking.getStatus() == Booking_status.PENDING) {
            bookingRepository.delete(booking);
        }
    }


}
