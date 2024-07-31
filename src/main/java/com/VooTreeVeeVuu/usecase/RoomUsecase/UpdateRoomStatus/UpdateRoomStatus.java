package com.VooTreeVeeVuu.usecase.RoomUsecase.UpdateRoomStatus;

import com.VooTreeVeeVuu.dto.GetAllRoomDTO;

import java.util.Optional;

public interface UpdateRoomStatus {
    Optional<GetAllRoomDTO> updateRoomStatus(Long id, GetAllRoomDTO roomDTO);
}
