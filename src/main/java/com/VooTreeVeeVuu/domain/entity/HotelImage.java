package com.VooTreeVeeVuu.domain.entity;

import com.VooTreeVeeVuu.dto.HotelImageDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HotelImages")
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    @Lob
    private byte[] imageBase64;

    private String imageType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelId")
    Hotel hotel;

    public HotelImageDTO toDTO() {
        HotelImageDTO dto = new HotelImageDTO();
        dto.setId(this.id);
        dto.setImageName(this.imageName);
        //dto.setImageBase64(Base64.getEncoder().encodeToString(this.imageBase64));
        dto.setImageType(this.imageType);
        return dto;
    }

    public HotelImage(Long id, String imageName, byte[] imageBase64, String imageType) {
        this.id = id;
        this.imageName = imageName;
        this.imageBase64 = imageBase64;
        this.imageType = imageType;
    }
}
