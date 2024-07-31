package com.VooTreeVeeVuu.usecase.HotelFacilityUsecase.GetHotelFacility;

import com.VooTreeVeeVuu.dto.GetAllHotelFacDTO;
import com.VooTreeVeeVuu.domain.entity.HotelFacility;
import com.VooTreeVeeVuu.domain.repository.HotelFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetHotelFacilityImpl implements GetHotelFacility{
	@Autowired
	private HotelFacilityRepository hotelFacilityRepository;

	public Optional<GetAllHotelFacDTO> getHotelFacilityById (Long id) {
		return hotelFacilityRepository.findById(id).map(this :: toDTO);
	}

	private GetAllHotelFacDTO toDTO (HotelFacility hotelFacility) {
		GetAllHotelFacDTO dto = new GetAllHotelFacDTO();
		dto.setId(hotelFacility.getId());
		dto.setFacility(hotelFacility.getFacility());
		dto.setHotel(hotelFacility.getHotel());
		return dto;
	}
}
