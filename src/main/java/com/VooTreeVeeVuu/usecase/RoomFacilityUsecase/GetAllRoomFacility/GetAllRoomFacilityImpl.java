package com.VooTreeVeeVuu.usecase.RoomFacilityUsecase.GetAllRoomFacility;


import com.VooTreeVeeVuu.domain.entity.RoomFacility;
import com.VooTreeVeeVuu.domain.repository.RoomFacilityRepository;
import com.VooTreeVeeVuu.dto.GetAllRoomFacDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllRoomFacilityImpl implements GetAllRoomFacility{
	@Autowired
	private RoomFacilityRepository roomFacilityRepository;

	public List<GetAllRoomFacDTO> getAllRoomFacility(){
		return roomFacilityRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
	}

	private GetAllRoomFacDTO toDTO(RoomFacility roomFacility) {
		GetAllRoomFacDTO dto = new GetAllRoomFacDTO();
		dto.setId(roomFacility.getId());
		dto.setFacility(roomFacility.getFacility());
		dto.setRoom(roomFacility.getRoom());
		return dto;
	}
}
