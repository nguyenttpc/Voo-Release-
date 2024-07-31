package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.domain.entity.RoomFacility;
import com.VooTreeVeeVuu.domain.entity.RoomType;
import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Room_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRoomDTO {
    private Long id;
    private int capacity;
    private double price;
    private Integer quantity;
    private double roomSize;
    private String description;
    private RoomType roomType;
    private boolean serveBreakfast;
    private Long hotelId;
    private String hotelName;
    private String ownerEmail;
    private Room_status status;
    private Edit_status edit_status;
    private List<RoomFacility> roomFacilities;
    private List<Booking> listBooking;
    private List<RoomImageDTO> room_images;
    private int availableRooms;
}
