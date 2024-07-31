package com.VooTreeVeeVuu.usecase.RoleUseCases.UpdateRole;

import com.VooTreeVeeVuu.dto.RoleDTO;

import java.util.Optional;

public interface UpdateRole {
	Optional<RoleDTO> updateRole (Long id, RoleDTO roleDTO);
}
