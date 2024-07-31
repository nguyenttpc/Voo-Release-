package com.VooTreeVeeVuu.controller;


import com.VooTreeVeeVuu.dto.GetAllHotelFacDTO;
import com.VooTreeVeeVuu.usecase.HotelFacilityUsecase.DeleteHotelFacility.DeleteHotelFacilityImpl;
import com.VooTreeVeeVuu.usecase.HotelFacilityUsecase.GetAllHotelFacility.GetAllHotelFacilityImpl;
import com.VooTreeVeeVuu.usecase.HotelFacilityUsecase.GetHotelFacility.GetHotelFacilityImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hotelFacilities")
public class HotelFacilityController {
    @Autowired
    private DeleteHotelFacilityImpl deleteHotelFacilityUseCase;

    @Autowired
    private GetAllHotelFacilityImpl getAllHotelFacilityUseCase;

    @Autowired
    private GetHotelFacilityImpl getHotelFacilityUseCase;

    @GetMapping()
    public List<GetAllHotelFacDTO> getAllHotelFacility(){
        return getAllHotelFacilityUseCase.getAllHotelFacility();
    }

    @GetMapping ("/{id}")
    public Optional<GetAllHotelFacDTO> getHotelFacilityById (@PathVariable Long id){
        return getHotelFacilityUseCase.getHotelFacilityById(id);
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteHotelFacility (@PathVariable Long id) {
        deleteHotelFacilityUseCase.deleteHotelFacility(id);
    }

}
