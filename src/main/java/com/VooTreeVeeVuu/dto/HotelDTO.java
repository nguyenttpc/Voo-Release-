package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Hotel_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    //private Long id;
    private String address;
    private String hotelName;
    private String city;
    private String hotelPhoneNum;
    private int hotelStars;
    private String hotelDescription;
    private Hotel_status status;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Long userId;
    private Long accommodationTypeId;
    private Edit_status edit_status;
    //private List<Rating> ratings;
    private List<RoomDTO> rooms;
    //private List<HotelImage> hotelImages;
    private List<HotelFacilityDTO> hotelFacilities;
}
