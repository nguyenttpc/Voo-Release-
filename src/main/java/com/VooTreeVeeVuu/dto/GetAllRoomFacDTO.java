package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Facility;
import com.VooTreeVeeVuu.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRoomFacDTO {
	private Long id;
	private Room room;
	private Facility facility;
}
