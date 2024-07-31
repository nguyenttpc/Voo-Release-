package com.VooTreeVeeVuu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassChangeDTO {
	private String oldPassword;
	private String newPassword;
	private String confPassword;
}
