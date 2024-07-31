package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.domain.repository.HotelRepository;
import com.VooTreeVeeVuu.dto.GetAllHotelDTO;
import com.VooTreeVeeVuu.dto.HotelDTO;
import com.VooTreeVeeVuu.services.HotelService;
import com.VooTreeVeeVuu.usecase.HotelUsecase.AcceptHotel.AcceptHotelRequest;
import com.VooTreeVeeVuu.usecase.HotelUsecase.DeleteHotel.DeleteHotelImpl;
import com.VooTreeVeeVuu.usecase.HotelUsecase.GetAllHotel.GetAllHotelImpl;
import com.VooTreeVeeVuu.usecase.HotelUsecase.GetHotel.GetHotelImpl;
import com.VooTreeVeeVuu.usecase.HotelUsecase.UpdateStatusHotel.UpdateStatusHotelImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private GetAllHotelImpl getAllHotelUseCase;

    @Autowired
    private GetHotelImpl getHotelUseCase;

    @Autowired
    private DeleteHotelImpl deleteHotelUseCase;

    @Autowired
    private UpdateStatusHotelImpl updateStatusHotelUseCase;

//    @Autowired
//    private ImagesUploadImpl imagesUploadUseCase;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private AcceptHotelRequest acceptHotelRequest;


    @PutMapping("/staff/accept/{id}")
    public Optional<GetAllHotelDTO> acceptHotel(@PathVariable Long id) {
        return acceptHotelRequest.acceptHotel(id);
    }

    @PutMapping("/staff/reject/{id}")
    public Optional<GetAllHotelDTO> rejectHotel(@PathVariable Long id) {
        return hotelService.rejectHotel(id);
    }

    @GetMapping()
    public List<GetAllHotelDTO> getAllHotel() {
        return getAllHotelUseCase.getAllHotel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAllHotelDTO> getHotelById(@PathVariable Long id,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkinDate,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkoutDate,
                                                       @RequestParam int rooms, @RequestParam int capacity) {
        if (!hotelService.validateDates(checkinDate, checkoutDate)) {
            return ResponseEntity.badRequest().body(null);
        }
        GetAllHotelDTO hotel = hotelService.getHotelByIdWithCriteria(id, checkinDate, checkoutDate, rooms, capacity);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/partner/getAllHotel/{userId}")
    public ResponseEntity<List<GetAllHotelDTO>> getAllHotelByUser(@PathVariable("userId") Long userId) {
        List<GetAllHotelDTO> list = hotelService.getAllHotelByUser(userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody @Valid HotelDTO hotelDTO) {
        HotelDTO createdHotel = hotelService.createHotel(hotelDTO);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @PutMapping("/partner/update/{id}")
    public ResponseEntity<HotelDTO> updatePartnerHotel(@PathVariable Long id, @RequestBody @Valid HotelDTO hotelDTO) {
        HotelDTO updatedHotel = hotelService.updateHotel(id, hotelDTO);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    @PutMapping("/staff/update/{id}")
    public Optional<GetAllHotelDTO> updateHotel(@RequestBody GetAllHotelDTO dto, @PathVariable Long id) {
        return updateStatusHotelUseCase.updateStatusHotel(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{hotelId}/images")
    public ResponseEntity<GetAllHotelDTO> uploadImages(@PathVariable Long hotelId,
                                                       @RequestParam("files") List<MultipartFile> files) {
        try {
            GetAllHotelDTO updatedHotel = hotelService.saveHotelImages(hotelId, files);
            return ResponseEntity.ok(updatedHotel);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetAllHotelDTO>> searchHotels(@RequestParam(required = false) String hotelName,
                                                             @RequestParam(required = false) String city,
                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkinDate,
                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkoutDate,
                                                             @RequestParam int rooms, @RequestParam int capacity) {

        // Kiểm tra tính hợp lệ của ngày
        if (!hotelService.validateDates(checkinDate, checkoutDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Kiểm tra xem ít nhất một trong hai tham số hotelName hoặc city phải có giá trị
        if ((hotelName == null || hotelName.isEmpty()) && (city == null || city.isEmpty())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Tìm kiếm các khách sạn
        List<GetAllHotelDTO> hotels;
        try {
            hotels = hotelService.searchHotels(hotelName, city, checkinDate, checkoutDate, rooms, capacity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (hotels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(hotels);
    }
}
