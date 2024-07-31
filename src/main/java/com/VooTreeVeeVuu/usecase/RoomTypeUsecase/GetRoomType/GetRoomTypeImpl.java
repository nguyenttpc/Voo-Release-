package com.VooTreeVeeVuu.usecase.RoomTypeUsecase.GetRoomType;



import com.VooTreeVeeVuu.dto.RoomTypeDTO;
import com.VooTreeVeeVuu.domain.entity.RoomType;
import com.VooTreeVeeVuu.domain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetRoomTypeImpl implements GetRoomType{
	@Autowired
	private RoomTypeRepository roomTypeRepository;

	public Optional<RoomTypeDTO> getRoomTypeById(Long id){
		return roomTypeRepository.findById(id).map(this :: toDTO);
	}

	private RoomTypeDTO toDTO(RoomType roomType) {
		RoomTypeDTO dto = new RoomTypeDTO();
		dto.setId(roomType.getId());
		dto.setRooms(roomType.getRooms());
		dto.setTypeName(roomType.getTypeName());
		return dto;
	}
}
