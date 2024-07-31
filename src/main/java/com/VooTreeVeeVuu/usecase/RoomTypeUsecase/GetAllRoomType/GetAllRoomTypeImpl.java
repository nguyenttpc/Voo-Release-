package com.VooTreeVeeVuu.usecase.RoomTypeUsecase.GetAllRoomType;

import com.VooTreeVeeVuu.dto.RoomTypeDTO;
import com.VooTreeVeeVuu.domain.entity.RoomType;
import com.VooTreeVeeVuu.domain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllRoomTypeImpl implements GetAllRoomType{
	@Autowired
	private RoomTypeRepository roomTypeRepository;

	public List<RoomTypeDTO> getAllRoomTypes(){
		return roomTypeRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
	}
	private RoomTypeDTO toDTO(RoomType roomType) {
		RoomTypeDTO dto = new RoomTypeDTO();
		dto.setId(roomType.getId());
		dto.setRooms(roomType.getRooms());
		dto.setTypeName(roomType.getTypeName());
		return dto;
	}
}
