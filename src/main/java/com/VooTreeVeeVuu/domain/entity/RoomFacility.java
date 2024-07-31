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
@Table (name = "RoomFacilities")
public class RoomFacility {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	//@NotBlank
	@ManyToOne
	@JoinColumn (name = "roomId")
	Room room;

	//@JsonIgnored
	//@NotBlank
	@ManyToOne
	@JoinColumn (name = "facId")
	Facility facility;

	public RoomFacility(Long id, Facility facility) {
		this.id = id;
		this.facility = facility;
	}
}
