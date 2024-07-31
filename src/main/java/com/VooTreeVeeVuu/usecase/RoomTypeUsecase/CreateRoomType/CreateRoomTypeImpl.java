package com.VooTreeVeeVuu.usecase.RoomTypeUsecase.CreateRoomType;

import com.VooTreeVeeVuu.dto.RoomTypeDTO;
import com.VooTreeVeeVuu.domain.entity.RoomType;
import com.VooTreeVeeVuu.domain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRoomTypeImpl implements CreateRoomType {
	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Transactional
	public RoomTypeDTO createRoomType (RoomTypeDTO roomTypeDTO){
		RoomType roomType = toEntity(roomTypeDTO);
		RoomType saved = roomTypeRepository.save(roomType);
		return toDTO(saved);
	}

	private RoomTypeDTO toDTO(RoomType roomType) {
		RoomTypeDTO dto = new RoomTypeDTO();
		dto.setRooms(roomType.getRooms());
		dto.setTypeName(roomType.getTypeName());
		return dto;
	}

	private RoomType toEntity(RoomTypeDTO roomTypeDTO) {
		RoomType roomType = new RoomType();
		roomType.setRooms(roomTypeDTO.getRooms());
		roomType.setTypeName(roomTypeDTO.getTypeName());
		return roomType;
	}
}
