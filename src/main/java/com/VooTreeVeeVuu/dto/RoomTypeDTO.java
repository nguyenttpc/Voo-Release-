package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDTO {
	private Long id;
	private String typeName;
	private List<Room> rooms;

	public RoomTypeDTO(Long id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}
}
