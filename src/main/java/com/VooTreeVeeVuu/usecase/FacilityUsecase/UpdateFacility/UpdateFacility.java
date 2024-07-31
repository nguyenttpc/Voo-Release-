package com.VooTreeVeeVuu.usecase.FacilityUsecase.UpdateFacility;

import com.VooTreeVeeVuu.dto.FacilityDTO;

import java.util.Optional;

public interface UpdateFacility {
	Optional<FacilityDTO> updateFacility(Long id, FacilityDTO facilityDTO);
}
