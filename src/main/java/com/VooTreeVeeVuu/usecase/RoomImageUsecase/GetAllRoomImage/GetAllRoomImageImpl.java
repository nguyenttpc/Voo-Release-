package com.VooTreeVeeVuu.usecase.RoomImageUsecase.GetAllRoomImage;

import com.VooTreeVeeVuu.domain.entity.RoomImage;
import com.VooTreeVeeVuu.domain.repository.RoomImageRepository;
import com.VooTreeVeeVuu.dto.RoomImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllRoomImageImpl implements GetAllRoomImage {
    @Autowired
    private RoomImageRepository roomImageRepository;

    public List<RoomImageDTO> getAllRoomImage() {
        return roomImageRepository.findAll().stream().map(this::convertToImageDTO).collect(Collectors.toList());
    }

    private RoomImageDTO convertToImageDTO(RoomImage image) {
        RoomImageDTO dto = new RoomImageDTO();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        //dto.setImageBase64(Base64.getEncoder().encodeToString(image.getImageBase64()));
        dto.setImageType(image.getImageType());
        dto.setImageUrl("/api/hotel-images/" + image.getId()); // Set URL
        return dto;
    }
}
