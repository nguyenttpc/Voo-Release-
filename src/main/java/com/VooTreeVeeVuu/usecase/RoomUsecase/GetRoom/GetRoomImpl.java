package com.VooTreeVeeVuu.usecase.RoomUsecase.GetRoom;

import com.VooTreeVeeVuu.domain.entity.Room;
import com.VooTreeVeeVuu.domain.entity.RoomImage;
import com.VooTreeVeeVuu.domain.repository.RoomRepository;
import com.VooTreeVeeVuu.dto.GetAllRoomDTO;
import com.VooTreeVeeVuu.dto.RoomImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GetRoomImpl implements GetRoom {
    @Autowired
    private RoomRepository roomRepository;

    public GetAllRoomDTO getRoomById(Long id) {
        return roomRepository.findById(id).map(this::toDTO).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    private GetAllRoomDTO toDTO(Room room) {
        GetAllRoomDTO dto = new GetAllRoomDTO();
        dto.setId(room.getId());
        dto.setCapacity(room.getCapacity());
        dto.setPrice(room.getPrice());
        dto.setQuantity(room.getQuantity());
        dto.setRoomSize(room.getRoomSize());
        //dto.setDescription(room.getDescription());
        dto.setRoomType(room.getRoomType());
        dto.setServeBreakfast(room.isServeBreakfast());
        dto.setHotelId(room.getHotel().getId());
        dto.setHotelName(room.getHotel().getHotelName());
        dto.setRoomFacilities(room.getRoomFacilities());
        dto.setRoom_images(room.getRoom_images().stream().map(this::convertToImageDTO).collect(Collectors.toList()));
        dto.setListBooking(room.getListBooking());
        dto.setOwnerEmail(room.getHotel().getUser().getAccount().getEmail());
        return dto;
    }

    private RoomImageDTO convertToImageDTO(RoomImage image) {
        RoomImageDTO dto = new RoomImageDTO();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        //dto.setImageBase64(Base64.getEncoder().encodeToString(image.getImageBase64()));
        dto.setImageType(image.getImageType());
        dto.setImageUrl("/api/room-images/" + image.getId()); // Set URL
        return dto;
    }
}
