package com.VooTreeVeeVuu.usecase.RoleUseCases.GetRole;

import com.VooTreeVeeVuu.dto.RoleDTO;
import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetRoleImpl implements GetRole  {
	@Autowired
	private RoleRepository roleRepository;

	public Optional<RoleDTO> getRoleById (Long id) {
		return roleRepository.findById(id).map(this :: toDTO);
	}

	private RoleDTO toDTO (Role role) {
		RoleDTO dto = new RoleDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}
}
