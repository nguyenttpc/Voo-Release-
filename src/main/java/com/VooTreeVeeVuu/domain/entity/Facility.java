package com.VooTreeVeeVuu.domain.entity;

import com.VooTreeVeeVuu.domain.utils.Fac_Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facId;

    @Column(name = "FacilityType")
    @Enumerated(EnumType.STRING)
    private Fac_Type facType;

    //@NotBlank
    @Column(columnDefinition = "nvarchar(200)", name = "FacilityName")
    private String facName;

    private String facIcon;

    @JsonIgnore
    @OneToMany(mappedBy = "facility")
    List<HotelFacility> hotelFacilities;

    @JsonIgnore
    @OneToMany(mappedBy = "facility")
    List<RoomFacility> roomFacilities;

    public Facility(Long id) {
        this.facId = id;
    }

    public Facility(Long facId, Fac_Type facType, String facName, String facIcon) {
        this.facId = facId;
        this.facType = facType;
        this.facName = facName;
        this.facIcon = facIcon;
    }
}