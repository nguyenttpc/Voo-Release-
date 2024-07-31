package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.HotelFacility;
import com.VooTreeVeeVuu.domain.entity.RoomFacility;
import com.VooTreeVeeVuu.domain.utils.Fac_Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDTO {
    private Long facId;
    private Fac_Type facType;
    private String facName;
    private String facIcon;
    private HotelFacility hotelFacility;
    private RoomFacility roomFacility;
}
