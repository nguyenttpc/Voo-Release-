package com.VooTreeVeeVuu.usecase.RatingUsecase.GetRatingForHotel;

import com.VooTreeVeeVuu.dto.RatingDTO;

import java.util.List;
import java.util.Optional;

public interface GetRatingForHotel {
	List<RatingDTO> getRatingsForHotel(Long hotelId);
}
