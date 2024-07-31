//package com.VooTreeVeeVuu.usecase.HotelUsecase.ImagesUpload;
//
//import com.VooTreeVeeVuu.domain.entity.Hotel;
//import com.VooTreeVeeVuu.domain.entity.HotelImage;
//import com.VooTreeVeeVuu.domain.repository.HotelImageRepository;
//import com.VooTreeVeeVuu.domain.repository.HotelRepository;
//import com.VooTreeVeeVuu.dto.HotelDTO;
//import com.VooTreeVeeVuu.dto.HotelImageDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ImagesUploadImpl implements ImagesUpload {
//    @Autowired
//    private HotelRepository hotelRepository;
//
//    @Autowired
//    private HotelImageRepository hotelImageRepository;
//
//    public Optional<HotelDTO> uploadImg(Long hotelId, List<HotelImageDTO> imageDTO) {
//        return hotelRepository.findById(hotelId).map(hotel -> {
//            List<HotelImage> hotelImages = imageDTO.stream().map(dto -> {
//                HotelImage hotelImage = new HotelImage();
//                //	hotelImage.setPath(dto.getPath());
//                hotelImage.setHotel(hotel);
//                return hotelImage;
//            }).toList();
//            hotel.getHotelImages().addAll(hotelImages);
//            return hotelRepository.save(hotel);
//        }).map(this::toDTO);
//    }
//
//    private HotelDTO toDTO(Hotel hotel) {
//        HotelDTO hotelDTO = new HotelDTO();
//        hotelDTO.setAddress(hotel.getAddress());
//        hotelDTO.setHotelName(hotel.getHotelName());
//        hotelDTO.setCity(hotel.getCity());
//        hotelDTO.setHotelPhoneNum(hotel.getHotelPhoneNum());
//        hotelDTO.setHotelStars(hotel.getHotelStars());
//        hotelDTO.setStatus(hotel.getStatus());
//        hotelDTO.setHotelDescription(hotel.getHotelDescription());
//        hotelDTO.setCheckInTime(hotel.getCheckInTime());
//        hotelDTO.setCheckOutTime(hotel.getCheckOutTime());
//        hotelDTO.setAccommodationTypeId(hotel.getAccommodationType().getId());
//        hotelDTO.setUserId(hotel.getUser().getId());
//        return hotelDTO;
//    }
//
//    public HotelImage toEntity(HotelImageDTO dto) {
//        HotelImage hotelImage = new HotelImage();
//        hotelImage.setId(dto.getId());
//        hotelImage.setPath(dto.getPath());
//        return hotelImage;
//    }
//}
