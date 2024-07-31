package com.VooTreeVeeVuu.usecase.HotelUsecase.GetAllHotel;

import com.VooTreeVeeVuu.domain.entity.Hotel;
import com.VooTreeVeeVuu.domain.entity.HotelImage;
import com.VooTreeVeeVuu.domain.entity.Room;
import com.VooTreeVeeVuu.domain.entity.RoomImage;
import com.VooTreeVeeVuu.domain.repository.HotelRepository;
import com.VooTreeVeeVuu.dto.GetAllHotelDTO;
import com.VooTreeVeeVuu.dto.GetAllRoomDTO;
import com.VooTreeVeeVuu.dto.HotelImageDTO;
import com.VooTreeVeeVuu.dto.RoomImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllHotelImpl implements GetAllHotel {
    @Autowired
    private HotelRepository hotelRepository;

    public List<GetAllHotelDTO> getAllHotel() {
        return hotelRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private GetAllHotelDTO toDTO(Hotel hotel) {
        GetAllHotelDTO hotelDTO = new GetAllHotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setCity(hotel.getCity());
        hotelDTO.setHotelPhoneNum(hotel.getHotelPhoneNum());
        hotelDTO.setHotelStars(hotel.getHotelStars());
        hotelDTO.setHotelDescription(hotel.getHotelDescription());
        hotelDTO.setCheckInTime(hotel.getCheckInTime());
        hotelDTO.setCheckOutTime(hotel.getCheckOutTime());
        hotelDTO.setStatus(hotel.getStatus());
        hotelDTO.setAccommodationType(hotel.getAccommodationType());
        hotelDTO.setHotelFacilities(hotel.getHotelFacilities());
        hotelDTO.setUser(hotel.getUser());
        hotelDTO.setHotelImages(
                hotel.getHotelImages().stream().map(this::convertToImageDTO).collect(Collectors.toList()));
        hotelDTO.setRooms(
                hotel.getRooms().stream().map(this::toRoomDTO).collect(Collectors.toList()));
        hotelDTO.setRatings(hotel.getListRating());
        hotelDTO.setEdit_status(hotel.getEdit_status());
        return hotelDTO;
    }

    private HotelImageDTO convertToImageDTO(HotelImage image) {
        HotelImageDTO dto = new HotelImageDTO();
        dto.setId(image.getId());
        //dto.setImageBase64(Base64.getEncoder().encodeToString(image.getImageBase64()));
        dto.setImageName(image.getImageName());
        dto.setImageType(image.getImageType());
        dto.setImageUrl("/api/hotel-images/" + image.getId()); // Set URL
        return dto;
    }

    private GetAllRoomDTO toRoomDTO(Room room) {
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
        dto.setRoom_images(room.getRoom_images().stream().map(this::convertToRoomImageDTO).collect(Collectors.toList()));
        dto.setListBooking(room.getListBooking());
        dto.setStatus(room.getStatus());
        dto.setEdit_status(room.getEdit_status());
        return dto;
    }

    private RoomImageDTO convertToRoomImageDTO(RoomImage image) {
        RoomImageDTO dto = new RoomImageDTO();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        //dto.setImageBase64(Base64.getEncoder().encodeToString(image.getImageBase64()));
        dto.setImageType(image.getImageType());
        dto.setImageUrl("/api/room-images/" + image.getId()); // Set URL
        return dto;
    }
}
