package com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.GetAccommodationType;

import com.VooTreeVeeVuu.dto.AccommodationTypeDTO;
import com.VooTreeVeeVuu.domain.entity.AccommodationType;
import com.VooTreeVeeVuu.domain.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetAccommodationTypeImpl implements GetAccommodationType{
    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    public Optional<AccommodationTypeDTO> getAccommodationTypeById(Long id){
        return accommodationTypeRepository.findById(id).map(this :: toDTO);
    }

    private AccommodationTypeDTO toDTO(AccommodationType accommodationType) {
        AccommodationTypeDTO dto = new AccommodationTypeDTO();
        dto.setId(accommodationType.getId());
        dto.setTypeName(accommodationType.getTypeName());
        return dto;
    }
}
