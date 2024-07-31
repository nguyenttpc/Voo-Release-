package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.dto.UserDTO;
import com.VooTreeVeeVuu.usecase.UserUseCase.GetAllUser.GetAllUserImpl;
import com.VooTreeVeeVuu.usecase.UserUseCase.GetUser.GetUserImpl;
import com.VooTreeVeeVuu.usecase.UserUseCase.UpdateUser.UpdateUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private GetAllUserImpl getAllUserUseCase;

    @Autowired
    private GetUserImpl getUserUseCase;

    @Autowired
    private UpdateUserImpl updateUserUseCase;

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return getAllUserUseCase.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return getUserUseCase.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public Optional<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return updateUserUseCase.updateUser(id, userDTO);
    }
}
