package com.VooTreeVeeVuu.usecase.RoomUsecase.CreateRoom;

import com.VooTreeVeeVuu.dto.RoomDTO;

public interface CreateRoom {
    RoomDTO createRoom(Long hotelId, RoomDTO roomDTO);
}
