package com.VooTreeVeeVuu.usecase.HotelFacilityUsecase.GetHotelFacility;

import com.VooTreeVeeVuu.dto.GetAllHotelFacDTO;

import java.util.Optional;

public interface GetHotelFacility {
	Optional<GetAllHotelFacDTO> getHotelFacilityById (Long id);
}
