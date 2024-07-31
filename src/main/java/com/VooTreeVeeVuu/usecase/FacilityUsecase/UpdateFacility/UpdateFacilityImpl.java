package com.VooTreeVeeVuu.usecase.FacilityUsecase.UpdateFacility;


import com.VooTreeVeeVuu.dto.FacilityDTO;
import com.VooTreeVeeVuu.domain.entity.Facility;
import com.VooTreeVeeVuu.domain.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateFacilityImpl implements UpdateFacility {
    @Autowired
    private FacilityRepository facilityRepository;

    @Transactional
    public Optional<FacilityDTO> updateFacility(Long id, FacilityDTO facilityDTO){
        return facilityRepository.findById(id).map(facility -> {
            facility.setFacType(facilityDTO.getFacType());
            facility.setFacName(facilityDTO.getFacName());
            facility.setFacIcon(facilityDTO.getFacIcon());
            Facility updated = facilityRepository.save(facility);
            return toDTO(updated);
        });
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
