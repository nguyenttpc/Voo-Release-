package com.VooTreeVeeVuu.usecase.RoleUseCases.FindRoleByName;

import com.VooTreeVeeVuu.dto.RoleDTO;
import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import com.VooTreeVeeVuu.domain.utils.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindRoleByNameImpl implements FindRoleByName{
	@Autowired
	private RoleRepository roleRepository;

	public List<RoleDTO> getRoleByName (RoleName name) {
		return roleRepository.findByName(name).stream().map(this :: toDTO).collect(Collectors.toList());
	}

	private RoleDTO toDTO (Role role) {
		RoleDTO dto = new RoleDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}
}
