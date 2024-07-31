package com.VooTreeVeeVuu.usecase.HotelUsecase.ImagesUpload;

import com.VooTreeVeeVuu.dto.HotelDTO;
import com.VooTreeVeeVuu.dto.HotelImageDTO;

import java.util.List;
import java.util.Optional;

public interface ImagesUpload {
	Optional<HotelDTO> uploadImg (Long hotelId, List<HotelImageDTO> imageDTO);
}
