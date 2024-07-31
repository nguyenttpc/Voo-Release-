package com.VooTreeVeeVuu.usecase.RatingUsecase.GetAllRating;

import com.VooTreeVeeVuu.dto.RatingDTO;
import com.VooTreeVeeVuu.domain.entity.Rating;
import com.VooTreeVeeVuu.domain.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllRatingImpl implements GetAllRating {
	@Autowired
	private RatingRepository ratingRepository;

	public List<RatingDTO> getAllRatings(){
		return ratingRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
	}

	private RatingDTO toDTO(Rating rating) {
		RatingDTO dto = new RatingDTO();
		dto.setId(rating.getId());
		dto.setComment(rating.getComment());
		dto.setRate(rating.getRate());
		dto.setHotel(rating.getHotel());
		dto.setUser(rating.getUser());
		return dto;
	}
}
