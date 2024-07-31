package com.VooTreeVeeVuu.usecase.RoleUseCases.FindRoleByName;

import com.VooTreeVeeVuu.domain.utils.RoleName;
import com.VooTreeVeeVuu.dto.RoleDTO;

import java.util.List;

public interface FindRoleByName {
	List<RoleDTO> getRoleByName (RoleName name);
}
