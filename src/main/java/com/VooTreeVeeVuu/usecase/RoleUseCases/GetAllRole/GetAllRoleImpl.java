package com.VooTreeVeeVuu.usecase.RoleUseCases.GetAllRole;

import com.VooTreeVeeVuu.dto.RoleDTO;
import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllRoleImpl implements GetAllRole{
	@Autowired
	private RoleRepository roleRepository;

	public List<RoleDTO> getAllRoles () {
		return roleRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
	}

	private RoleDTO toDTO (Role role) {
		RoleDTO dto = new RoleDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}
}
