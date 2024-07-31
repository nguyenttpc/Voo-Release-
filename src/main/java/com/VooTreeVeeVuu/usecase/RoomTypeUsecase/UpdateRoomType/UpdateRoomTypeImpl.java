package com.VooTreeVeeVuu.usecase.RoomTypeUsecase.UpdateRoomType;


import com.VooTreeVeeVuu.dto.RoomTypeDTO;
import com.VooTreeVeeVuu.domain.entity.RoomType;
import com.VooTreeVeeVuu.domain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateRoomTypeImpl implements UpdateRoomType {
	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Transactional
	public Optional<RoomTypeDTO> updateRoomType(Long id, RoomTypeDTO roomTypeDTO){
		return roomTypeRepository.findById(id).map(roomType -> {
			roomType.setRooms(roomTypeDTO.getRooms());
			roomType.setTypeName(roomTypeDTO.getTypeName());
			RoomType updated = roomTypeRepository.save(roomType);
			return toDTO(updated);
		});
	}

	private RoomTypeDTO toDTO(RoomType roomType) {
		RoomTypeDTO dto = new RoomTypeDTO();
		dto.setRooms(roomType.getRooms());
		dto.setTypeName(roomType.getTypeName());
		return dto;
	}
}
