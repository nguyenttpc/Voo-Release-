package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Facility;
import com.VooTreeVeeVuu.domain.entity.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllHotelFacDTO {
	private Long id;
	private Facility facility;
	private Hotel hotel;
}
