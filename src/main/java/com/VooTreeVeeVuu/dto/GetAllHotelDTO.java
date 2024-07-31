package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.AccommodationType;
import com.VooTreeVeeVuu.domain.entity.HotelFacility;
import com.VooTreeVeeVuu.domain.entity.Rating;
import com.VooTreeVeeVuu.domain.entity.User;
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
public class GetAllHotelDTO {
    private Long id;
    private String address;
    private String hotelName;
    private String city;
    private String hotelPhoneNum;
    private int hotelStars;
    private String hotelDescription;
    private Hotel_status status;
    private Edit_status edit_status;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private AccommodationType accommodationType;
    private User user;
    private List<HotelImageDTO> hotelImages;
    private List<GetAllRoomDTO> rooms;
    private List<HotelFacility> hotelFacilities;
    private List<Rating> ratings;

}
