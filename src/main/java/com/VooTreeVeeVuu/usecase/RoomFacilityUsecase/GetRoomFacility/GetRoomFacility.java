package com.VooTreeVeeVuu.usecase.RoomFacilityUsecase.GetRoomFacility;

import com.VooTreeVeeVuu.dto.GetAllRoomFacDTO;

import java.util.Optional;

public interface GetRoomFacility {
	Optional<GetAllRoomFacDTO> getRoomFacById (Long id);
}
