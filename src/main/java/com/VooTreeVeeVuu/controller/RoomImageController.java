package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.domain.entity.RoomImage;
import com.VooTreeVeeVuu.domain.repository.RoomImageRepository;
import com.VooTreeVeeVuu.dto.RoomImageDTO;
import com.VooTreeVeeVuu.services.RoomService;
import com.VooTreeVeeVuu.usecase.RoomImageUsecase.GetAllRoomImage.GetAllRoomImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/room-images")
public class RoomImageController {
    @Autowired
    private GetAllRoomImageImpl getAllRoomImageUseCase;
    @Autowired
    private RoomImageRepository roomImageRepository;
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<RoomImageDTO> getAllRoomImages() {
        return getAllRoomImageUseCase.getAllRoomImage();
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long imageId) {
        RoomImage image = roomImageRepository.findById(imageId).orElseThrow(
                () -> new RuntimeException("Image not found"));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getImageType())).header(
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getImageName() + "\"").body(
                new ByteArrayResource(image.getImageBase64()));
    }

    @DeleteMapping("/remove-all")
    public ResponseEntity<String> removeAllRoomImages(
            @RequestParam Long hotelId,
            @RequestParam Long roomId) {
        roomService.removeAllRoomImages(hotelId, roomId);
        return ResponseEntity.ok("All images removed successfully");
    }

    @DeleteMapping("/remove-img")
    public ResponseEntity<String> removeSpecificRoomImages(
            @RequestParam Long hotelId,
            @RequestParam Long roomId,
            @RequestParam List<Long> imageIds) {
        roomService.removeRoomImages(hotelId, roomId, imageIds);
        return ResponseEntity.ok("Specific images removed successfully");
    }

}
