package com.VooTreeVeeVuu.usecase.RoleUseCases.GetRole;

import com.VooTreeVeeVuu.dto.RoleDTO;

import java.util.Optional;

public interface GetRole {
	Optional<RoleDTO> getRoleById (Long id);
}
