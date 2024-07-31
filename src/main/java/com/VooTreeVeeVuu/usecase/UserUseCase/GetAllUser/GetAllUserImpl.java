package com.VooTreeVeeVuu.usecase.UserUseCase.GetAllUser;

import com.VooTreeVeeVuu.dto.UserDTO;
import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllUserImpl implements GetAllUser {
	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getAllUsers () {
		return userRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
	}

	private UserDTO toDTO (User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setDob(String.valueOf(user.getDob()));
		userDTO.setGender(user.getGender());
		userDTO.setAccount(user.getAccount());
		return userDTO;
	}
}
