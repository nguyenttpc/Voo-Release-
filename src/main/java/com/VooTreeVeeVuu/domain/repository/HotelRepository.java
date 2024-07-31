package com.VooTreeVeeVuu.domain.repository;

import com.VooTreeVeeVuu.domain.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByHotelNameOrCity(String hotelName, String city);

    List<Hotel> findByUserId(Long id);
}