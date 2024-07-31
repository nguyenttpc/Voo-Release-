package com.VooTreeVeeVuu.usecase.RoleUseCases.CreateRole;

import com.VooTreeVeeVuu.dto.RoleDTO;
import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import com.VooTreeVeeVuu.domain.utils.RoleName;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateRoleImpl implements CreateRole {
	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public RoleDTO createRole (RoleName roleName) {
		Optional<Role> existed = roleRepository.findByName(roleName);
		if (existed.isPresent())
		{
			throw new IllegalArgumentException("Role already existed");
		}
		Role role = new Role();
		role.setName(roleName);
		Role saved = roleRepository.save(role);
		return toDTO(saved);
	}

	private RoleDTO toDTO (Role role) {
		RoleDTO dto = new RoleDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}
}
