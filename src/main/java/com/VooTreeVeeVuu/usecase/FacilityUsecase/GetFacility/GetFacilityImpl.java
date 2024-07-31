package com.VooTreeVeeVuu.usecase.FacilityUsecase.GetFacility;

import com.VooTreeVeeVuu.dto.FacilityDTO;
import com.VooTreeVeeVuu.domain.entity.Facility;
import com.VooTreeVeeVuu.domain.repository.FacilityRepository;
import com.VooTreeVeeVuu.usecase.FacilityUsecase.GetAllFacility.GetAllFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetFacilityImpl implements GetFacility {
    @Autowired
    private FacilityRepository facilityRepository;

    public Optional<FacilityDTO> getFacilityById(Long id){
        return facilityRepository.findById(id).map(this :: toDTO);
    }

    private FacilityDTO toDTO(Facility facility) {
        FacilityDTO dto = new FacilityDTO();
        dto.setFacId(facility.getFacId());
        dto.setFacType(facility.getFacType());
        dto.setFacName(facility.getFacName());
        dto.setFacIcon(facility.getFacIcon());
        return dto;
    }
}
