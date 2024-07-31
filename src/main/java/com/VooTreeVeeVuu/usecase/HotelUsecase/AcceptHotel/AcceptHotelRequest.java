package com.VooTreeVeeVuu.usecase.HotelUsecase.AcceptHotel;

import com.VooTreeVeeVuu.dto.GetAllHotelDTO;

import java.util.Optional;

public interface AcceptHotelRequest {
    Optional<GetAllHotelDTO> acceptHotel(Long id);
}
