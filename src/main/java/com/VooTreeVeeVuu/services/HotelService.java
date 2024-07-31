package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.domain.entity.*;
import com.VooTreeVeeVuu.domain.repository.*;
import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Hotel_status;
import com.VooTreeVeeVuu.domain.utils.Room_status;
import com.VooTreeVeeVuu.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelFacilityRepository hotelFacilityRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private RoomFacilityRepository roomFacilityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelImageRepository hotelImageRepository;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Autowired
    private RoomImageRepository roomImageRepository;

    public Optional<GetAllHotelDTO> rejectHotel(Long id) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setStatus(Hotel_status.REJECTED);
            Hotel updated = hotelRepository.save(hotel);

            scheduler.schedule(() -> {
                deleteIfReject(updated.getId());
            }, 30, TimeUnit.SECONDS);

            return toDTO(updated);
        });
    }

    public void deleteIfReject(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Not found"));
        if (hotel.getStatus() == Hotel_status.REJECTED) {
            deleteHotel(hotelId);
        }
    }

    @Transactional
    public void removeAllHotelImages(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
        List<HotelImage> list = hotel.getHotelImages();
        hotelImageRepository.deleteAll(list);
    }

    @Transactional
    public void removeHotelImage(Long hotelId, Long imageId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        HotelImage image = hotel.getHotelImages().stream()
                .filter(img -> img.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Image not found in hotel"));

        hotel.getHotelImages().remove(image);
        hotelImageRepository.delete(image);
    }

//    @Transactional
//    public void removeAllHotelImages(Long hotelId) {
//        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
//        List<HotelImage> list = hotel.getHotelImages();
//        hotelImageRepository.deleteAll(list);
//    }
//
//    @Transactional
//    public void removeHotelImage(Long hotelId, Long imageId) {
//        Hotel hotel = hotelRepository.findById(hotelId)
//                .orElseThrow(() -> new RuntimeException("Hotel not found"));
//
//        HotelImage image = hotel.getHotelImages().stream()
//                .filter(img -> img.getId().equals(imageId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Image not found in hotel"));
//
//        hotel.getHotelImages().remove(image);
//        hotelImageRepository.delete(image);
//    }

    public GetAllHotelDTO saveHotelImages(Long hotelId, List<MultipartFile> files) throws IOException {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));

        List<HotelImage> images = new ArrayList<>();
        for (MultipartFile file : files) {
            HotelImage hotelImage = new HotelImage();
            hotelImage.setImageName(file.getOriginalFilename());
            hotelImage.setImageBase64(file.getBytes());
            hotelImage.setImageType(file.getContentType());
            hotelImage.setHotel(hotel);
            images.add(hotelImage);
        }
        hotel.getHotelImages().addAll(images);
        hotelImageRepository.saveAll(images);
        Hotel saved = hotelRepository.save(hotel);
        return toDTO(saved);
    }

    public List<GetAllHotelDTO> getAllHotelByUser(Long id) {
        //User partner = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Partner not found"));
        return hotelRepository.findByUserId(id).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = mapToHotelEntity(hotelDTO);
        hotel.setEdit_status(Edit_status.CREATE);
        hotel.setStatus(Hotel_status.PENDING);
        Hotel savedHotel = hotelRepository.save(hotel);

        if (hotelDTO.getHotelFacilities() != null) {
            for (HotelFacilityDTO facilityDTO : hotelDTO.getHotelFacilities()) {
                HotelFacility hotelFacility = new HotelFacility();
                hotelFacility.setHotel(savedHotel);
                hotelFacility.setFacility(new Facility(facilityDTO.getFacilityId()));
                hotelFacilityRepository.save(hotelFacility);
            }
        }

        if (hotelDTO.getRooms() != null) {
            for (RoomDTO roomDTO : hotelDTO.getRooms()) {
                RoomType roomType = roomTypeRepository.findById(roomDTO.getRoomTypeId()).orElseThrow(
                        () -> new RuntimeException("RoomType not found with id: " + roomDTO.getRoomTypeId()));
                Room room = mapToRoomEntity(roomDTO, savedHotel, roomType);
                room.setEdit_status(Edit_status.CREATE);
                room.setStatus(Room_status.PENDING);
                roomRepository.save(room);
                if (roomDTO.getRoomFacilities() != null) {
                    for (RoomFacilityDTO roomFacilityDTO : roomDTO.getRoomFacilities()) {
                        Facility facility = facilityRepository.findById(roomFacilityDTO.getFacilityId()).orElseThrow(
                                () -> new RuntimeException(
                                        "Facility not found with id: " + roomFacilityDTO.getFacilityId()));
                        RoomFacility roomFacility = new RoomFacility();
                        roomFacility.setRoom(room);
                        roomFacilityRepository.save(roomFacility);
                    }
                    roomRepository.save(room);
                }
            }
        }
        return mapToHotelDTO(savedHotel);
    }

    @Transactional
    public HotelDTO updateHotel(Long hotelId, HotelDTO hotelDTO) {
        Hotel existingHotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new RuntimeException("Hotel not found with id: " + hotelId));
        existingHotel.setStatus(Hotel_status.PENDING);
        existingHotel.setEdit_status(Edit_status.UPDATE);
        updateHotelEntity(existingHotel, hotelDTO);
        Hotel updatedHotel = hotelRepository.save(existingHotel);

        updateHotelFacilities(updatedHotel, hotelDTO.getHotelFacilities());

        return mapToHotelDTO(updatedHotel);
    }

    @Transactional
    public void deleteHotel(Long id) {
        Hotel existed = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));

        existed.getRooms().forEach(room -> {
            roomFacilityRepository.deleteAll(room.getRoomFacilities());
            roomImageRepository.deleteAll(room.getRoom_images());
        });

        roomRepository.deleteAll(existed.getRooms());

        hotelFacilityRepository.deleteAll(existed.getHotelFacilities());

        hotelImageRepository.deleteAll(existed.getHotelImages());

        hotelRepository.delete(existed);
    }

    // Lấy chi tiết khách sạn và lọc theo tiêu chí tìm kiếm
    public GetAllHotelDTO getHotelByIdWithCriteria(Long id, LocalDate checkinDate, LocalDate checkoutDate, int rooms,
                                                   int capacity) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Hotel not found with id: " + id));

        // Lọc các phòng dựa trên tiêu chí tìm kiếm
        //hotel.setRooms(filterRooms(hotel.getRooms(), checkinDate, checkoutDate, rooms, capacity));
        List<GetAllRoomDTO> filteredRoom = filterRooms(hotel.getRooms(), checkinDate, checkoutDate, rooms, capacity);
        return searchtoDTO(hotel, filteredRoom);
    }

    public List<GetAllHotelDTO> searchHotels(String hotelName, String city, LocalDate checkinDate,
                                             LocalDate checkoutDate, int rooms, int capacity) {
        if (!validateDates(checkinDate, checkoutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }

        List<Hotel> hotels;
        if (hotelName != null && !hotelName.isEmpty() && city != null && !city.isEmpty()) {
            hotels = hotelRepository.findByHotelNameOrCity(hotelName, city);
        } else {
            throw new IllegalArgumentException("Either hotelName or city must be provided.");
        }

        // Lọc các khách sạn dựa trên số lượng phòng trống, sức chứa và ngày, sau đó chuyển đổi thành DTO
        return hotels.stream()
                .map(hotel -> {
                    List<GetAllRoomDTO> filteredRooms = filterRooms(hotel.getRooms(), checkinDate, checkoutDate, rooms, capacity);
                    if (!filteredRooms.isEmpty()) {
                        return searchtoDTO(hotel, filteredRooms);
                    }
                    return null;
                })
                .filter(Objects::nonNull) // Chỉ giữ khách sạn có phòng thỏa mãn điều kiện
                .collect(Collectors.toList());
    }

    private List<GetAllRoomDTO> filterRooms(List<Room> rooms, LocalDate checkinDate, LocalDate checkoutDate, int requiredRooms, int capacity) {
        return rooms.stream()
                .filter(room -> room.getCapacity() >= capacity)

                .map(room -> {
                    int availableRooms = getAvailableRooms(room, checkinDate, checkoutDate);
                    if (availableRooms >= requiredRooms) {
                        GetAllRoomDTO roomDTO = toRoomDTO(room);
                        roomDTO.setAvailableRooms(availableRooms);
                        return roomDTO;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private int getAvailableRooms(Room room, LocalDate checkinDate, LocalDate checkoutDate) {
        long bookedRooms = room.getListBooking().stream()
                .filter(booking -> booking.getCheckInDate().isBefore(checkoutDate) && booking.getCheckOutDate().isAfter(checkinDate))
                .mapToInt(Booking::getNumOfRoom) // Lấy số lượng phòng đã đặt trong mỗi booking
                .sum();
        return room.getQuantity() - (int) bookedRooms;
    }

    public boolean validateDates(LocalDate checkinDate, LocalDate checkoutDate) {
        return checkinDate != null && checkoutDate != null && checkinDate.isBefore(checkoutDate);
    }


    private GetAllHotelDTO searchtoDTO(Hotel hotel, List<GetAllRoomDTO> filteredRoom) {
        GetAllHotelDTO hotelDTO = new GetAllHotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setCity(hotel.getCity());
        hotelDTO.setHotelPhoneNum(hotel.getHotelPhoneNum());
        hotelDTO.setHotelStars(hotel.getHotelStars());
        hotelDTO.setHotelDescription(hotel.getHotelDescription());
        hotelDTO.setCheckInTime(hotel.getCheckInTime());
        hotelDTO.setCheckOutTime(hotel.getCheckOutTime());
        hotelDTO.setStatus(hotel.getStatus());
        hotelDTO.setAccommodationType(hotel.getAccommodationType());
        hotelDTO.setHotelFacilities(hotel.getHotelFacilities());
        hotelDTO.setUser(hotel.getUser());
        hotelDTO.setHotelImages(
                hotel.getHotelImages().stream().map(this::convertToImageDTO).collect(Collectors.toList()));
        hotelDTO.setRooms(filteredRoom);
        hotelDTO.setRatings(hotel.getListRating());
        return hotelDTO;
    }

    private GetAllHotelDTO toDTO(Hotel hotel) {
        GetAllHotelDTO hotelDTO = new GetAllHotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setCity(hotel.getCity());
        hotelDTO.setHotelPhoneNum(hotel.getHotelPhoneNum());
        hotelDTO.setHotelStars(hotel.getHotelStars());
        hotelDTO.setHotelDescription(hotel.getHotelDescription());
        hotelDTO.setCheckInTime(hotel.getCheckInTime());
        hotelDTO.setCheckOutTime(hotel.getCheckOutTime());
        hotelDTO.setStatus(hotel.getStatus());
        hotelDTO.setAccommodationType(hotel.getAccommodationType());
        hotelDTO.setHotelFacilities(hotel.getHotelFacilities());
        hotelDTO.setUser(hotel.getUser());
        hotelDTO.setHotelImages(
                hotel.getHotelImages().stream().map(this::convertToImageDTO).collect(Collectors.toList()));
        hotelDTO.setRooms(
                hotel.getRooms().stream().map(this::toRoomDTO).collect(Collectors.toList()));
        hotelDTO.setRatings(hotel.getListRating());
        hotelDTO.setEdit_status(hotel.getEdit_status());
        return hotelDTO;
    }

    private void updateHotelFacilities(Hotel hotel, List<HotelFacilityDTO> facilityDTOs) {
        List<HotelFacility> existingFacilities = hotelFacilityRepository.findByHotel(hotel);
        hotelFacilityRepository.deleteAll(existingFacilities);

        if (facilityDTOs != null) {
            for (HotelFacilityDTO facilityDTO : facilityDTOs) {
                Facility facility = facilityRepository.findById(facilityDTO.getFacilityId()).orElseThrow(
                        () -> new RuntimeException("Facility not found with id: " + facilityDTO.getFacilityId()));
                HotelFacility hotelFacility = new HotelFacility();
                hotelFacility.setHotel(hotel);
                hotelFacility.setFacility(facility);
                hotelFacilityRepository.save(hotelFacility);
            }
        }
    }

    private Room mapToRoomEntity(RoomDTO roomDTO, Hotel hotel, RoomType roomType) {
        Room room = new Room();
        room.setCapacity(roomDTO.getCapacity());
        room.setPrice(roomDTO.getPrice());
        room.setQuantity(roomDTO.getQuantity());
        room.setRoomSize(roomDTO.getRoomSize());
        //room.setDescription(roomDTO.getDescription());
        room.setServeBreakfast(roomDTO.isServeBreakfast());
        room.setHotel(hotel);
        room.setRoomType(roomType);
        return room;
    }

    private Hotel mapToHotelEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setHotelPhoneNum(hotelDTO.getHotelPhoneNum());
        hotel.setHotelStars(hotelDTO.getHotelStars());
        hotel.setHotelDescription(hotelDTO.getHotelDescription());
        hotel.setStatus(hotelDTO.getStatus());
        hotel.setCheckInTime(hotelDTO.getCheckInTime());
        hotel.setCheckOutTime(hotelDTO.getCheckOutTime());
        hotel.setAccommodationType(new AccommodationType(hotelDTO.getAccommodationTypeId()));
        hotel.setUser(new User(hotelDTO.getUserId()));
        hotel.setEdit_status(hotelDTO.getEdit_status());
        return hotel;
    }

    private HotelDTO mapToHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setCity(hotel.getCity());
        hotelDTO.setHotelPhoneNum(hotel.getHotelPhoneNum());
        hotelDTO.setHotelStars(hotel.getHotelStars());
        hotelDTO.setStatus(hotel.getStatus());
        hotelDTO.setHotelDescription(hotel.getHotelDescription());
        hotelDTO.setCheckInTime(hotel.getCheckInTime());
        hotelDTO.setCheckOutTime(hotel.getCheckOutTime());
        hotelDTO.setAccommodationTypeId(hotel.getAccommodationType().getId());
        hotelDTO.setUserId(hotel.getUser().getId());
        hotelDTO.setEdit_status(hotel.getEdit_status());
        return hotelDTO;
    }

    private void updateHotelEntity(Hotel hotel, HotelDTO hotelDTO) {
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setHotelPhoneNum(hotelDTO.getHotelPhoneNum());
        hotel.setHotelStars(hotelDTO.getHotelStars());
        hotel.setHotelDescription(hotelDTO.getHotelDescription());
        hotel.setCheckInTime(hotelDTO.getCheckInTime());
        hotel.setCheckOutTime(hotelDTO.getCheckOutTime());
        hotel.setAccommodationType(new AccommodationType(hotelDTO.getAccommodationTypeId()));
        hotel.setUser(new User(hotelDTO.getUserId()));
        hotel.setStatus(hotelDTO.getStatus());
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

    private GetAllRoomDTO toRoomDTO(Room room) {
        GetAllRoomDTO dto = new GetAllRoomDTO();
        dto.setId(room.getId());
        dto.setCapacity(room.getCapacity());
        dto.setPrice(room.getPrice());
        dto.setQuantity(room.getQuantity());
        dto.setRoomSize(room.getRoomSize());
        //dto.setDescription(room.getDescription());
        dto.setRoomType(room.getRoomType());
        dto.setServeBreakfast(room.isServeBreakfast());
        dto.setHotelId(room.getHotel().getId());
        dto.setHotelName(room.getHotel().getHotelName());
        dto.setRoomFacilities(room.getRoomFacilities());
        dto.setRoom_images(room.getRoom_images().stream().map(this::convertToImageDTO).collect(Collectors.toList()));
        dto.setListBooking(room.getListBooking());
        dto.setStatus(room.getStatus());
        dto.setEdit_status(room.getEdit_status());
        //dto.setAvailableRooms(getAvailableRooms());
        return dto;
    }

    private RoomImageDTO convertToImageDTO(RoomImage image) {
        RoomImageDTO dto = new RoomImageDTO();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        //dto.setImageBase64(Base64.getEncoder().encodeToString(image.getImageBase64()));
        dto.setImageType(image.getImageType());
        dto.setImageUrl("/api/room-images/" + image.getId()); // Set URL
        return dto;
    }
}
