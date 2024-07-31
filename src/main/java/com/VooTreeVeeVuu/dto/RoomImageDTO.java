package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomImageDTO {
	private Long id;
	private String imageName;
	private String imageBase64; // Base64 encoded string
	private String imageType;
	private String imageUrl;

	public String getImageUrl() {
		return "/api/room-images/" + this.id; // Construct URL
	}
}
