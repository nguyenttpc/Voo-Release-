package com.VooTreeVeeVuu.usecase.UserUseCase.GetUser;

import com.VooTreeVeeVuu.dto.UserDTO;

public interface GetUser {
    UserDTO getUserById(Long id);
}
