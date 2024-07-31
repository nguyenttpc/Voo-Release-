package com.VooTreeVeeVuu.usecase.UserUseCase.UpdateUser;

import com.VooTreeVeeVuu.dto.UserDTO;

import java.util.Optional;

public interface UpdateUser {
	Optional<UserDTO> updateUser (Long id, UserDTO userDTO);
}
