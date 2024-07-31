package com.VooTreeVeeVuu.usecase.HotelImageUsecase.GetAllHotelImage;

import com.VooTreeVeeVuu.domain.entity.HotelImage;
import com.VooTreeVeeVuu.domain.repository.HotelImageRepository;
import com.VooTreeVeeVuu.dto.HotelImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllHotelImageImpl implements GetAllHotelImage {
    @Autowired
    private HotelImageRepository hotelImageRepository;

    public List<HotelImageDTO> getAllHotelImage() {
        return hotelImageRepository.findAll().stream().map(this::convertToImageDTO).collect(Collectors.toList());
    }

    private HotelImageDTO convertToImageDTO(HotelImage image) {
        HotelImageDTO dto = new HotelImageDTO();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        //dto.setImageBase64(Base64.getEncoder().encodeToString(image.getImageBase64()));
        dto.setImageType(image.getImageType());
        dto.setImageUrl("/api/hotel-images/" + image.getId()); // Set URL
        return dto;
    }
}
