package com.VooTreeVeeVuu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelWithDetailsDTO {
	private HotelDTO hotelDTO;
	private List<RoomDTO> rooms;
	private List<HotelFacilityDTO> facilities;
}
