package com.VooTreeVeeVuu.usecase.RoomUsecase.UpdateRoomStatus;

import com.VooTreeVeeVuu.domain.entity.Room;
import com.VooTreeVeeVuu.domain.repository.RoomRepository;
import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.dto.GetAllRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRoomStatusImpl implements UpdateRoomStatus {
    @Autowired
    private RoomRepository roomRepository;


    @Override
    public Optional<GetAllRoomDTO> updateRoomStatus(Long id, GetAllRoomDTO roomDTO) {
        return roomRepository.findById(id).map(room -> {
            room.setStatus(roomDTO.getStatus());
            room.setEdit_status(Edit_status.ACTIVE);
            Room updated = roomRepository.save(room);
            return toDTO(updated);
        });
    }

    private GetAllRoomDTO toDTO(Room room) {
        GetAllRoomDTO dto = new GetAllRoomDTO();
        dto.setStatus(room.getStatus());
        return dto;
    }
}
