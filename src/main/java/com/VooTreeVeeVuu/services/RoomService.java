package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.domain.entity.*;
import com.VooTreeVeeVuu.domain.repository.*;
import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Room_status;
import com.VooTreeVeeVuu.dto.GetAllRoomDTO;
import com.VooTreeVeeVuu.dto.RoomDTO;
import com.VooTreeVeeVuu.dto.RoomFacilityDTO;
import com.VooTreeVeeVuu.dto.RoomImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private RoomFacilityRepository roomFacilityRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomImageRepository roomImageRepository;

    @Transactional
    public void removeAllRoomImages(Long hotelId, Long roomId) {
        roomRepository.findByIdAndHotelId(roomId, hotelId)
                .orElseThrow(() -> new RuntimeException("Room not found in specified hotel"));

        List<RoomImage> imagesToRemove = roomImageRepository.findByRoomId(roomId);
        roomImageRepository.deleteAll(imagesToRemove);
    }

    @Transactional
    public void removeRoomImages(Long hotelId, Long roomId, List<Long> imageIds) {
        roomRepository.findByIdAndHotelId(roomId, hotelId)
                .orElseThrow(() -> new RuntimeException("Room not found in specified hotel"));

        List<RoomImage> imagesToRemove = roomImageRepository.findAllById(imageIds);
        roomImageRepository.deleteAll(imagesToRemove);
    }

    @Transactional
    public RoomDTO updateRooms(Long roomId, RoomDTO roomDTO
                               //, List<MultipartFile> files
    ) throws IOException {
        Room existedRoom = roomRepository.findById(roomId).orElseThrow(
                () -> new RuntimeException("Room not found with id: " + roomId));

        // Update Room Entity
        updateRoomEntity(existedRoom, roomDTO);
        existedRoom.setStatus(Room_status.PENDING);
        existedRoom.setEdit_status(Edit_status.UPDATE);
        Room updatedRoom = roomRepository.save(existedRoom);

        Hotel hotel = hotelRepository.findById(existedRoom.getHotel().getId()).orElseThrow(
                () -> new RuntimeException("Hotel not found"));
        //hotel.setStatus(Hotel_status.PENDING);
        hotelRepository.save(hotel);

        updateRoomFacilities(updatedRoom, roomDTO.getRoomFacilities());

        // Update Room Images
        //updateRoomImages(updatedRoom, files);

        return mapToRoomDTO(updatedRoom);
    }


    private void updateRoomFacilities(Room room, List<RoomFacilityDTO> roomFacilityDTOs) {
//        List<RoomFacility> existedList = roomFacilityRepository.findByRoom(room);
//
//        roomFacilityRepository.deleteAll(existedList);

        room.getRoomFacilities().clear();

        if (roomFacilityDTOs != null) {
            for (RoomFacilityDTO roomFacilityDTO : roomFacilityDTOs) {
                Facility facility = facilityRepository.findById(roomFacilityDTO.getFacilityId()).orElseThrow(
                        () -> new RuntimeException("Facility not found with id: " + roomFacilityDTO.getFacilityId()));
                RoomFacility roomFacility = new RoomFacility();
                roomFacility.setRoom(room);
                roomFacility.setFacility(facility);
                roomFacilityRepository.save(roomFacility);
            }
        }
    }


    //  @Transactional
//    public void updateRoomImages(Long roomId, List<MultipartFile> files) throws IOException {
    // Clear existing images
//        Room findRoom = roomRepository.findById(id)
////                .orElseThrow(() -> new RuntimeException("Room not found"));
////
////        findRoom.getRoom_images().clear();
////        roomImageRepository.deleteByRoom(findRoom);
////        // Add new images
////        List<RoomImage> images = new ArrayList<>();
////        for (MultipartFile file : files) {
////            RoomImage roomImage = new RoomImage();
////            roomImage.setImageName(file.getOriginalFilename());
////            roomImage.setImageBase64(file.getBytes());
////            roomImage.setImageType(file.getContentType());
////            roomImage.setRoom(findRoom);
////            images.add(roomImage);
////        }
////
////        findRoom.getRoom_images().addAll(images);
////        roomImageRepository.saveAll(images);
////        roomRepository.save(findRoom);
//
//    }


    public GetAllRoomDTO updateRoomImages(Long roomId, List<MultipartFile> files) throws IOException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        room.getRoom_images().clear();
        //roomImageRepository.deleteByRoom(room);
        List<RoomImage> images = new ArrayList<>();
        for (MultipartFile file : files) {
            RoomImage roomImage = new RoomImage();
            roomImage.setImageName(file.getOriginalFilename());
            roomImage.setImageBase64(file.getBytes());
            roomImage.setImageType(file.getContentType());
            roomImage.setRoom(room);
            images.add(roomImage);
        }
        room.getRoom_images().addAll(images);
        roomImageRepository.saveAll(images);
        Room saved = roomRepository.save(room);
        return toDTO(saved);
    }


    public GetAllRoomDTO saveRoomImages(Long roomId, List<MultipartFile> files) throws IOException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));

        List<RoomImage> images = new ArrayList<>();
        for (MultipartFile file : files) {
            RoomImage roomImage = new RoomImage();
            roomImage.setImageName(file.getOriginalFilename());
            roomImage.setImageBase64(file.getBytes());
            roomImage.setImageType(file.getContentType());
            roomImage.setRoom(room);
            images.add(roomImage);
        }
        room.getRoom_images().addAll(images);
        roomImageRepository.saveAll(images);
        Room saved = roomRepository.save(room);
        return toDTO(saved);
    }

    private void updateRoomEntity(Room room, RoomDTO roomDTO) {
        room.setCapacity(roomDTO.getCapacity());
        room.setPrice(roomDTO.getPrice());
        room.setQuantity(roomDTO.getQuantity());
        room.setRoomSize(roomDTO.getRoomSize());
        //   room.setDescription(roomDTO.getDescription());
        room.setServeBreakfast(roomDTO.isServeBreakfast());

        if (roomDTO.getRoomTypeId() != null) {
            RoomType roomType = roomTypeRepository.findById(roomDTO.getRoomTypeId()).orElseThrow(
                    () -> new RuntimeException("RoomType not found with id: " + roomDTO.getRoomTypeId()));
            room.setRoomType(roomType);
        }

        // Update associations with existing Hotel
        if (roomDTO.getHotelId() != null) {
            Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElseThrow(
                    () -> new RuntimeException("Hotel not found with id: " + roomDTO.getHotelId()));
            room.setHotel(hotel);
        }
    }

    private void mapToRoomEntity(RoomDTO roomDTO, Room room) {
        room.setCapacity(roomDTO.getCapacity());
        room.setPrice(roomDTO.getPrice());
        room.setQuantity(roomDTO.getQuantity());
        room.setRoomSize(roomDTO.getRoomSize());
        //  room.setDescription(roomDTO.getDescription());
        room.setServeBreakfast(roomDTO.isServeBreakfast());
    }

    private RoomDTO mapToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setQuantity(room.getQuantity());
        roomDTO.setRoomSize(room.getRoomSize());
        //   roomDTO.setDescription(room.getDescription());
        roomDTO.setRoomTypeId(room.getRoomType().getId());
        roomDTO.setServeBreakfast(room.isServeBreakfast());
        roomDTO.setHotelId(room.getHotel().getId());
        roomDTO.setRoom_images(room.getRoom_images().stream().map(this::convertToImageDTO).collect(Collectors.toList()));
        roomDTO.setRoomFacilities(room.getRoomFacilities().stream().map(rf -> {
            RoomFacilityDTO facilityDTO = new RoomFacilityDTO();
            facilityDTO.setFacilityId(rf.getFacility().getFacId());
            return facilityDTO;
        }).collect(Collectors.toList()));

        return roomDTO;
    }

    private GetAllRoomDTO toDTO(Room room) {
        GetAllRoomDTO dto = new GetAllRoomDTO();
        dto.setId(room.getId());
        dto.setCapacity(room.getCapacity());
        dto.setPrice(room.getPrice());
        dto.setQuantity(room.getQuantity());
        dto.setRoomSize(room.getRoomSize());
        //   dto.setDescription(room.getDescription());
        dto.setRoomType(room.getRoomType());
        dto.setServeBreakfast(room.isServeBreakfast());
        dto.setHotelId(room.getHotel().getId());
        dto.setHotelName(room.getHotel().getHotelName());
        dto.setRoomFacilities(room.getRoomFacilities());
        dto.setRoom_images(room.getRoom_images().stream().map(this::convertToImageDTO).collect(Collectors.toList()));
        dto.setListBooking(room.getListBooking());
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
