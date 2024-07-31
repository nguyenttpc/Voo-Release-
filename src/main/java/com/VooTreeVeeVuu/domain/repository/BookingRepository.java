package com.VooTreeVeeVuu.domain.repository;

import com.VooTreeVeeVuu.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT o FROM Booking o WHERE o.user.id = ?1 AND o.status LIKE 'PAID'")
    List<Booking> findBookingHistoryByUser(Long userId);
}