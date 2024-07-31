package com.VooTreeVeeVuu.usecase.RoleUseCases.UpdateRole;

import com.VooTreeVeeVuu.dto.RoleDTO;
import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRoleImpl implements UpdateRole{
	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public Optional<RoleDTO> updateRole (Long id, RoleDTO roleDTO) {
		return roleRepository.findById(id).map(role -> {
			role.setName(roleDTO.getName());
			Role updated = roleRepository.save(role);
			return toDTO(updated);
		});
	}

	private RoleDTO toDTO (Role role) {
		RoleDTO dto = new RoleDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}
}
