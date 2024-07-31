package com.VooTreeVeeVuu.usecase.HotelUsecase.UpdateStatusHotel;

import com.VooTreeVeeVuu.domain.entity.Hotel;
import com.VooTreeVeeVuu.domain.repository.HotelRepository;
import com.VooTreeVeeVuu.dto.GetAllHotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateStatusHotelImpl implements UpdateStatusHotel{
    @Autowired
    private HotelRepository hotelRepository;

    public Optional<GetAllHotelDTO> updateStatusHotel(Long id, GetAllHotelDTO hotelDTO) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setStatus(hotelDTO.getStatus());
            Hotel updated = hotelRepository.save(hotel);
            return toDTO(updated);
        });
    }

    private GetAllHotelDTO toDTO(Hotel hotel) {
        GetAllHotelDTO dto = new GetAllHotelDTO();
        dto.setStatus(hotel.getStatus());
        return dto;
    }
}
