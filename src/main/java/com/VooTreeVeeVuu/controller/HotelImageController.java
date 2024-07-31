package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.domain.entity.HotelImage;
import com.VooTreeVeeVuu.domain.repository.HotelImageRepository;
import com.VooTreeVeeVuu.dto.HotelImageDTO;
import com.VooTreeVeeVuu.usecase.HotelImageUsecase.GetAllHotelImage.GetAllHotelImage;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin ("*")
@RestController
@RequestMapping ("/api/hotel-images")
public class HotelImageController {

	@Autowired
	private HotelImageRepository hotelImageRepository;

	@Autowired
	private GetAllHotelImage getAllHotelImage;

	@GetMapping ()
	public ResponseEntity<List<HotelImageDTO>> getAllImages () {
		List<HotelImageDTO> list = getAllHotelImage.getAllHotelImage();
		return ResponseEntity.ok(list);
	}

	@GetMapping ("/{imageId}")
	public ResponseEntity<ByteArrayResource> getImage (@PathVariable Long imageId) {
		HotelImage image = hotelImageRepository.findById(imageId).orElseThrow(
				() -> new RuntimeException("Image not found"));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getImageType())).header(
				HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getImageName() + "\"").body(
				new ByteArrayResource(image.getImageBase64()));
	}
}
