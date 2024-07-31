package com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.GetAccommodationType;

import com.VooTreeVeeVuu.dto.AccommodationTypeDTO;
import java.util.Optional;

public interface GetAccommodationType {
	Optional<AccommodationTypeDTO> getAccommodationTypeById(Long id);
}
