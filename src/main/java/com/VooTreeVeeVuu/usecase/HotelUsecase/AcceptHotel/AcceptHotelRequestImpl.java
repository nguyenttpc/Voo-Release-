package com.VooTreeVeeVuu.usecase.HotelUsecase.AcceptHotel;

import com.VooTreeVeeVuu.domain.entity.Hotel;
import com.VooTreeVeeVuu.domain.repository.HotelRepository;
import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Hotel_status;
import com.VooTreeVeeVuu.domain.utils.Room_status;
import com.VooTreeVeeVuu.dto.GetAllHotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcceptHotelRequestImpl implements AcceptHotelRequest {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Optional<GetAllHotelDTO> acceptHotel(Long id) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setStatus(Hotel_status.ACTIVE);
            hotel.setEdit_status(Edit_status.ACTIVE);

            hotel.getRooms().forEach(room -> {
                room.setStatus(Room_status.ACTIVE);
                room.setEdit_status(Edit_status.ACTIVE);
            });
            Hotel accepted = hotelRepository.save(hotel);
            return toDTO(accepted);
        });
    }

    private GetAllHotelDTO toDTO(Hotel hotel) {
        GetAllHotelDTO dto = new GetAllHotelDTO();
        dto.setStatus(hotel.getStatus());
        dto.setEdit_status(hotel.getEdit_status());
        return dto;
    }
}
