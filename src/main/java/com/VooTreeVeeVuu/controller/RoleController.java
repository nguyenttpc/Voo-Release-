package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.dto.RoleDTO;
import com.VooTreeVeeVuu.domain.utils.RoleName;
import com.VooTreeVeeVuu.usecase.RoleUseCases.CreateRole.CreateRoleImpl;
import com.VooTreeVeeVuu.usecase.RoleUseCases.DeleteRole.DeleteRoleImpl;
import com.VooTreeVeeVuu.usecase.RoleUseCases.FindRoleByName.FindRoleByNameImpl;
import com.VooTreeVeeVuu.usecase.RoleUseCases.GetAllRole.GetAllRoleImpl;
import com.VooTreeVeeVuu.usecase.RoleUseCases.GetRole.GetRoleImpl;
import com.VooTreeVeeVuu.usecase.RoleUseCases.UpdateRole.UpdateRoleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin ("*")
@RestController
@RequestMapping ("/api/roles")
public class RoleController {
	@Autowired
	private GetAllRoleImpl getAllRoleUseCase;

	@Autowired
	private GetRoleImpl getRoleUseCase;

	@Autowired
	private CreateRoleImpl createRoleUseCase;

	@Autowired
	private DeleteRoleImpl deleteRoleUseCase;

	@Autowired
	private FindRoleByNameImpl findRoleByNameUseCase;

	@Autowired
	private UpdateRoleImpl updateRoleUseCase;

	@GetMapping ()
	public List<RoleDTO> getAllRoles () {
		return getAllRoleUseCase.getAllRoles();
	}

	@GetMapping ("/{id}")
	public Optional<RoleDTO> getRole (@PathVariable Long id) {
		return Optional.of(
				getRoleUseCase.getRoleById(id).orElseThrow(() -> new IllegalArgumentException("Role not found")));
	}

	@GetMapping ("/search")
	public List<RoleDTO> findRoleByName (@RequestParam RoleName name) {
		return findRoleByNameUseCase.getRoleByName(name);
	}

	@PostMapping
	public RoleDTO createRole (@RequestBody RoleDTO roleDTO) {
		return createRoleUseCase.createRole(roleDTO.getName());
	}

	@PutMapping ("/{id}")
	public Optional<RoleDTO> updateRole (@RequestBody RoleDTO roleDTO, @PathVariable Long id) {
		return updateRoleUseCase.updateRole(id, roleDTO);
	}

	@DeleteMapping ("/{id}")
	public void deleteRole (@PathVariable Long id) {
		deleteRoleUseCase.deleteRole(id);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
