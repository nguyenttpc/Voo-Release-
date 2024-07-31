package com.VooTreeVeeVuu.usecase.FacilityUsecase.GetFacility;

import com.VooTreeVeeVuu.dto.FacilityDTO;

import java.util.Optional;

public interface GetFacility {
	Optional<FacilityDTO> getFacilityById(Long id);
}
