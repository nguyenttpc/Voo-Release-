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
@Table (name= "room_type")
public class RoomType {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column (columnDefinition = "nvarchar(50)", name = "typeName")
    private String typeName;

    @JsonIgnore
    @OneToMany (mappedBy = "roomType")
    List<Room> rooms;

	public RoomType (Long roomTypeId) {
	    this.id = roomTypeId;
	}

    public RoomType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }
}
