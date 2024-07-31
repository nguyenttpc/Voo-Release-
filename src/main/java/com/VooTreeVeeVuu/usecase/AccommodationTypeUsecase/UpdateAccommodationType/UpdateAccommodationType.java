package com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.UpdateAccommodationType;

import com.VooTreeVeeVuu.dto.AccommodationTypeDTO;

import java.util.Optional;

public interface UpdateAccommodationType {
	Optional<AccommodationTypeDTO> updateAccommodationTypeDTO(Long id, AccommodationTypeDTO typeDTO);
}
