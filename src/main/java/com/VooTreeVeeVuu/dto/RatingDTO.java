package com.VooTreeVeeVuu.dto;


import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.domain.entity.Hotel;
import com.VooTreeVeeVuu.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    private Long id;
    private int rate;
    private String comment;
    private LocalDate date;
    private User user;
    private Hotel hotel;
    private Booking booking;

    public RatingDTO(Long id, int rate, String comment, LocalDate date, User user, Hotel hotel) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.hotel = hotel;
    }
}
