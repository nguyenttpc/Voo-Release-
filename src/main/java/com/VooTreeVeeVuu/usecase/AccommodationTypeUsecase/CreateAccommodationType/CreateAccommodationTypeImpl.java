package com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.CreateAccommodationType;

import com.VooTreeVeeVuu.dto.AccommodationTypeDTO;
import com.VooTreeVeeVuu.domain.entity.AccommodationType;
import com.VooTreeVeeVuu.domain.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateAccommodationTypeImpl implements CreateAccommodationType {
    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    @Transactional
    public AccommodationTypeDTO createAccommodationType (AccommodationTypeDTO typeDTO){
        AccommodationType accommodationType = toEntity(typeDTO);
        AccommodationType saved = accommodationTypeRepository.save(accommodationType);
        return toDTO(saved);
    }

    private AccommodationTypeDTO toDTO(AccommodationType accommodationType) {
        AccommodationTypeDTO dto = new AccommodationTypeDTO();
        dto.setId(accommodationType.getId());
        dto.setTypeName(accommodationType.getTypeName());
        return dto;
    }

    private AccommodationType toEntity(AccommodationTypeDTO typeDTO) {
        AccommodationType accommodationType = new AccommodationType();
        accommodationType.setId(typeDTO.getId());
        accommodationType.setTypeName(typeDTO.getTypeName());
        return accommodationType;
    }
}
