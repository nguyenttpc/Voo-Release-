package com.VooTreeVeeVuu.domain.entity;

import com.VooTreeVeeVuu.dto.RoomImageDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_images")
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    @Lob
    private byte[] imageBase64;

    private String imageType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "roomId")
    Room room;

    public RoomImageDTO toDTO() {
        RoomImageDTO dto = new RoomImageDTO();
        dto.setId(this.id);
        dto.setImageName(this.imageName);
        //dto.setImageBase64(Base64.getEncoder().encodeToString(this.imageBase64));
        dto.setImageType(this.imageType);
        return dto;
    }

    public RoomImage(Long id, String imageName, byte[] imageBase64, String imageType) {
        this.id = id;
        this.imageName = imageName;
        this.imageBase64 = imageBase64;
        this.imageType = imageType;
    }
}
