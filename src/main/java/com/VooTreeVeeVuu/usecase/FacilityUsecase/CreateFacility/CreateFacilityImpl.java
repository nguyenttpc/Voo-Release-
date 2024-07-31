package com.VooTreeVeeVuu.usecase.FacilityUsecase.CreateFacility;

import com.VooTreeVeeVuu.dto.FacilityDTO;
import com.VooTreeVeeVuu.domain.entity.Facility;
import com.VooTreeVeeVuu.domain.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateFacilityImpl implements CreateFacility{
    @Autowired
    private FacilityRepository facilityRepository;

    @Transactional
    public FacilityDTO createFacility (FacilityDTO facilityDTO){
        Facility facility = toEntity(facilityDTO);
        Facility saved = facilityRepository.save(facility);
        return toDTO(saved);
    }

    private FacilityDTO toDTO(Facility facility) {
        FacilityDTO dto = new FacilityDTO();
        dto.setFacId(facility.getFacId());
        dto.setFacType(facility.getFacType());
        dto.setFacName(facility.getFacName());
        dto.setFacIcon(facility.getFacIcon());
        return dto;
    }

    private Facility toEntity(FacilityDTO facilityDTO) {
        Facility facility = new Facility();
        facility.setFacId(facilityDTO.getFacId());
        facility.setFacType(facilityDTO.getFacType());
        facility.setFacName(facilityDTO.getFacName());
        facility.setFacIcon(facilityDTO.getFacIcon());
        return facility;
    }
}
