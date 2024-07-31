package com.VooTreeVeeVuu.usecase.UserUseCase.GetUser;

import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.repository.UserRepository;
import com.VooTreeVeeVuu.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserImpl implements GetUser {
    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::toDTO).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDob(String.valueOf(user.getDob()));
        userDTO.setGender(user.getGender());
        return userDTO;
    }
}
