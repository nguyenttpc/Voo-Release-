package com.VooTreeVeeVuu.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="accommodation_type")
public class AccommodationType {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "nvarchar(50)", name = "typeName")
    private String typeName;//Hotel, Motel, Resort, Homestay, Apartment

    @JsonIgnore
    @OneToMany( mappedBy = "accommodationType")
    List<Hotel> hotels;

	public AccommodationType (Long accommodationTypeId) {
	    this.id = accommodationTypeId;
	}

    public AccommodationType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }
}
