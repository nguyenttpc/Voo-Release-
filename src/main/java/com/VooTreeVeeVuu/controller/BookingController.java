package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.dto.BookingDTO;
import com.VooTreeVeeVuu.dto.InsertBookingDTO;
import com.VooTreeVeeVuu.services.BookingService;
import com.VooTreeVeeVuu.usecase.BookingUsecase.GetAllBooking.GetAllBookingImpl;
import com.VooTreeVeeVuu.usecase.BookingUsecase.GetBooking.GetBookingImpl;
import com.VooTreeVeeVuu.usecase.BookingUsecase.UpdateReviewStatus.UpdateReviewStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private GetAllBookingImpl getAllBookingUseCase;

    @Autowired
    private GetBookingImpl getBookingUseCase;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UpdateReviewStatus updateReviewStatus;

    @GetMapping()
    public List<BookingDTO> getAllBooking() {
        return getAllBookingUseCase.getAllBooking();
    }

    @GetMapping("/{id}")
    public Optional<BookingDTO> getBookingById(@PathVariable Long id) {
        return getBookingUseCase.getBookingById(id);
    }

    @GetMapping("/{userId}/booking-history")
    public List<BookingDTO> getUserBookingHistory(@PathVariable Long userId) {
        return bookingService.getUserBookingHistory(userId);
    }

    @PostMapping
    public ResponseEntity<InsertBookingDTO> createBooking(@RequestBody InsertBookingDTO insertBookingDTO) {
        InsertBookingDTO create = bookingService.createBooking(insertBookingDTO);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @PutMapping("/update/review-status/{id}")
    public Optional<Booking> updateReviewStatus(@PathVariable("id") Long id) {
        return updateReviewStatus.updateReviewStatus(id);
    }
}
