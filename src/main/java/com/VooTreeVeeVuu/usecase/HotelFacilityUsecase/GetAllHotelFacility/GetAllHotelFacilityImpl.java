package com.VooTreeVeeVuu.usecase.HotelFacilityUsecase.GetAllHotelFacility;

import com.VooTreeVeeVuu.dto.GetAllHotelFacDTO;
import com.VooTreeVeeVuu.domain.entity.HotelFacility;
import com.VooTreeVeeVuu.domain.repository.HotelFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllHotelFacilityImpl implements GetAllHotelFacility {
	@Autowired
	private HotelFacilityRepository hotelFacilityRepository;

	public List<GetAllHotelFacDTO> getAllHotelFacility () {
		return hotelFacilityRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
	}

	private GetAllHotelFacDTO toDTO (HotelFacility hotelFacility) {
		GetAllHotelFacDTO dto = new GetAllHotelFacDTO();
		dto.setId(hotelFacility.getId());
		dto.setFacility(hotelFacility.getFacility());
		dto.setHotel(hotelFacility.getHotel());
		return dto;
	}
}
