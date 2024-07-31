package com.VooTreeVeeVuu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	private String username;
	private String password;
	private LocalDate date = LocalDate.now();
}
