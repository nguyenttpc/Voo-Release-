package com.VooTreeVeeVuu.usecase.RoomTypeUsecase.GetRoomType;

import com.VooTreeVeeVuu.dto.RoomTypeDTO;

import java.util.Optional;

public interface GetRoomType {
	Optional<RoomTypeDTO> getRoomTypeById(Long id);
}
