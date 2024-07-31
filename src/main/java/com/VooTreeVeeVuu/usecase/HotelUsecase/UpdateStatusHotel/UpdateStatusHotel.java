package com.VooTreeVeeVuu.usecase.HotelUsecase.UpdateStatusHotel;

import com.VooTreeVeeVuu.dto.GetAllHotelDTO;

import java.util.Optional;

public interface UpdateStatusHotel {
	Optional<GetAllHotelDTO> updateStatusHotel(Long id, GetAllHotelDTO hotelDTO);
}
