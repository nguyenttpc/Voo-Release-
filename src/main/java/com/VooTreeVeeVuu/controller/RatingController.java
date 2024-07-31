package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.dto.CreateRatingDTO;
import com.VooTreeVeeVuu.dto.RatingDTO;
import com.VooTreeVeeVuu.usecase.RatingUsecase.CreateRating.CreateRating;
import com.VooTreeVeeVuu.usecase.RatingUsecase.GetAllRating.GetAllRating;
import com.VooTreeVeeVuu.usecase.RatingUsecase.GetRatingForHotel.GetRatingForHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private CreateRating createRatingUseCase;

    @Autowired
    private GetAllRating getAllRatingUseCase;

    @Autowired
    private GetRatingForHotel getRatingsForHotel;

    @GetMapping()
    public List<RatingDTO> getAllRating() {
        return getAllRatingUseCase.getAllRatings();
    }

    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody CreateRatingDTO createRatingDTO) {
        RatingDTO ratingDTO = createRatingUseCase.createRating(createRatingDTO);
        return ResponseEntity.ok(ratingDTO);
    }


    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RatingDTO>> getRatingsForHotel(@PathVariable Long hotelId) {
        List<RatingDTO> ratings = getRatingsForHotel.getRatingsForHotel(hotelId);
        return ResponseEntity.ok(ratings);
    }
}
