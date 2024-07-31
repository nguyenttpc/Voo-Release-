package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
	private Long id;
	private RoleName name;
}
