package com.VooTreeVeeVuu.domain.repository;

import com.VooTreeVeeVuu.domain.entity.Hotel;
import com.VooTreeVeeVuu.domain.entity.HotelFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelFacilityRepository extends JpaRepository<HotelFacility, Long> {
	List<HotelFacility> findByHotel (Hotel hotel);
}