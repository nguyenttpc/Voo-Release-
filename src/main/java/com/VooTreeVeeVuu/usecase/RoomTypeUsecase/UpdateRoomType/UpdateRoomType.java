package com.VooTreeVeeVuu.usecase.RoomTypeUsecase.UpdateRoomType;

import com.VooTreeVeeVuu.dto.RoomTypeDTO;

import java.util.Optional;

public interface UpdateRoomType {
	Optional<RoomTypeDTO> updateRoomType(Long id, RoomTypeDTO roomTypeDTO);
}
