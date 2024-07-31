package com.VooTreeVeeVuu.usecase.RatingUsecase.GetRatingForHotel;

import com.VooTreeVeeVuu.dto.RatingDTO;
import com.VooTreeVeeVuu.domain.entity.Rating;
import com.VooTreeVeeVuu.domain.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetRatingForHotelImpl implements GetRatingForHotel {
	@Autowired
	private RatingRepository ratingRepository;

	private RatingDTO convertToDTO(Rating rating) {
		RatingDTO dto = new RatingDTO();
		dto.setId(rating.getId());
		dto.setRate(rating.getRate());
		dto.setComment(rating.getComment());
		dto.setDate(rating.getDate());
		dto.setHotel(rating.getHotel());
		dto.setUser(rating.getUser());
		return dto;
	}


	@Override
	public List<RatingDTO> getRatingsForHotel (Long hotelId) {
		List<Rating> ratings = ratingRepository.findByHotelId(hotelId);
		return ratings.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
