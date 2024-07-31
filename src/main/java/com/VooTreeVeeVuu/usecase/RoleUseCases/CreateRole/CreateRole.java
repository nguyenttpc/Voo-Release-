package com.VooTreeVeeVuu.usecase.RoleUseCases.CreateRole;

import com.VooTreeVeeVuu.domain.utils.RoleName;
import com.VooTreeVeeVuu.dto.RoleDTO;

public interface CreateRole {
	RoleDTO createRole (RoleName roleName);
}
