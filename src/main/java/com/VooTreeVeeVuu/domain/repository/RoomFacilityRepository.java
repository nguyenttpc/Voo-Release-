package com.VooTreeVeeVuu.domain.repository;

import com.VooTreeVeeVuu.domain.entity.Room;
import com.VooTreeVeeVuu.domain.entity.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomFacilityRepository extends JpaRepository<RoomFacility, Long> {
	List<RoomFacility> findByRoom (Room room);
}