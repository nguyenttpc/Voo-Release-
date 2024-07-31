package com.VooTreeVeeVuu.usecase.RoomFacilityUsecase.GetRoomFacility;

import com.VooTreeVeeVuu.domain.entity.RoomFacility;
import com.VooTreeVeeVuu.domain.repository.RoomFacilityRepository;
import com.VooTreeVeeVuu.dto.GetAllRoomFacDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetRoomFacilityImpl implements GetRoomFacility {
	@Autowired
	private RoomFacilityRepository roomFacilityRepository;

	public Optional<GetAllRoomFacDTO> getRoomFacById (Long id) {
		return roomFacilityRepository.findById(id).map(this :: toDTO);
	}

	private GetAllRoomFacDTO toDTO (RoomFacility roomFacility) {
		GetAllRoomFacDTO dto = new GetAllRoomFacDTO();
		dto.setId(roomFacility.getId());
		dto.setFacility(roomFacility.getFacility());
		dto.setRoom(roomFacility.getRoom());
		return dto;
	}
}
