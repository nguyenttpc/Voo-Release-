package com.VooTreeVeeVuu.usecase.HotelUsecase.GetHotel;

import com.VooTreeVeeVuu.dto.GetAllHotelDTO;

import java.util.Optional;

public interface GetHotel {
	GetAllHotelDTO getHotelById(Long id);
}
