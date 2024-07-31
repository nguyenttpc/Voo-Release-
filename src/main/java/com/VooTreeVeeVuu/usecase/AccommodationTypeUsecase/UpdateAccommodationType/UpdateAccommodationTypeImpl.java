package com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.UpdateAccommodationType;

import com.VooTreeVeeVuu.domain.entity.AccommodationType;
import com.VooTreeVeeVuu.domain.repository.AccommodationTypeRepository;
import com.VooTreeVeeVuu.dto.AccommodationTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateAccommodationTypeImpl implements UpdateAccommodationType {
    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    @Transactional
    public Optional<AccommodationTypeDTO> updateAccommodationTypeDTO (Long id, AccommodationTypeDTO typeDTO){
        return accommodationTypeRepository.findById(id).map(accommodationType -> {
            accommodationType.setId(typeDTO.getId());
            accommodationType.setTypeName(typeDTO.getTypeName());
            AccommodationType updated = accommodationTypeRepository.save(accommodationType);
            return toDTO(updated);
        });
    }

    private AccommodationTypeDTO toDTO(AccommodationType accommodationType) {
        AccommodationTypeDTO dto = new AccommodationTypeDTO();
        dto.setId(accommodationType.getId());
        dto.setTypeName(accommodationType.getTypeName());
        return dto;
    }
}
