package com.VooTreeVeeVuu.usecase.RoomUsecase.CreateRoom;

import com.VooTreeVeeVuu.domain.entity.*;
import com.VooTreeVeeVuu.domain.repository.*;
import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Room_status;
import com.VooTreeVeeVuu.dto.RoomDTO;
import com.VooTreeVeeVuu.dto.RoomFacilityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRoomImpl implements CreateRoom {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private RoomFacilityRepository roomFacilityRepository;

    @Override
    public RoomDTO createRoom(Long hotelId, RoomDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
        RoomType roomType = roomTypeRepository.findById(roomDTO.getRoomTypeId()).orElseThrow(
                () -> new RuntimeException("RoomType not found with id: " + roomDTO.getRoomTypeId()));
        Room room = mapToRoomEntity(roomDTO, hotel, roomType);
        room.setEdit_status(Edit_status.CREATE);
        room.setStatus(Room_status.PENDING);
        Room createRoom = roomRepository.save(room);
        if (roomDTO.getRoomFacilities() != null) {
            for (RoomFacilityDTO facilityDTO : roomDTO.getRoomFacilities()) {
                RoomFacility roomFacility = new RoomFacility();
                roomFacility.setRoom(createRoom);
                roomFacility.setFacility(new Facility(facilityDTO.getFacilityId()));
                roomFacilityRepository.save(roomFacility);
            }
        }
        return mapToRoomDTO(createRoom);
    }

    private Room mapToRoomEntity(RoomDTO roomDTO, Hotel hotel, RoomType roomType) {
        Room room = new Room();
        room.setCapacity(roomDTO.getCapacity());
        room.setPrice(roomDTO.getPrice());
        room.setQuantity(roomDTO.getQuantity());
        room.setRoomSize(roomDTO.getRoomSize());
        //   room.setDescription(roomDTO.getDescription());
        room.setServeBreakfast(roomDTO.isServeBreakfast());
        room.setRoomType(roomType);
        room.setHotel(hotel);
        room.setStatus(roomDTO.getStatus());
        return room;
    }

    private RoomDTO mapToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setQuantity(room.getQuantity());
        roomDTO.setRoomSize(room.getRoomSize());
        // roomDTO.setDescription(room.getDescription());
        roomDTO.setRoomTypeId(room.getRoomType().getId());
        roomDTO.setServeBreakfast(room.isServeBreakfast());
        roomDTO.setHotelId(room.getHotel().getId());
        roomDTO.setStatus(room.getStatus());
        return roomDTO;
    }
}
