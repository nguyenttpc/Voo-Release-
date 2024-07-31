package com.VooTreeVeeVuu.usecase.HotelUsecase.DeleteHotel;

import com.VooTreeVeeVuu.domain.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteHotelImpl implements DeleteHotel {
    @Autowired
    private HotelRepository hotelRepository;

    @Transactional
    public void deleteHotel(Long id){hotelRepository.deleteById(id);
    }
}
