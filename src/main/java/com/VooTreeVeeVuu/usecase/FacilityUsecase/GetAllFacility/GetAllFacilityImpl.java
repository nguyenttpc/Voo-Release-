package com.VooTreeVeeVuu.usecase.FacilityUsecase.GetAllFacility;

import com.VooTreeVeeVuu.dto.FacilityDTO;
import com.VooTreeVeeVuu.domain.entity.Facility;
import com.VooTreeVeeVuu.domain.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllFacilityImpl implements GetAllFacility{
    @Autowired
    private FacilityRepository facilityRepository;

    public List<FacilityDTO> getAllFacility(){
        return facilityRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
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
