package com.VooTreeVeeVuu.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "HotelFacilities")
public class HotelFacility {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	//@JsonIgnored
	//@NotBlank
	@ManyToOne
	@JoinColumn (name = "facId")
	private Facility facility;

	@JsonIgnore
	//@NotBlank
	@ManyToOne
	@JoinColumn (name = "hotelId")
	private Hotel hotel;

	public HotelFacility(Long id, Facility facility) {
		this.id = id;
		this.facility = facility;
	}
}
