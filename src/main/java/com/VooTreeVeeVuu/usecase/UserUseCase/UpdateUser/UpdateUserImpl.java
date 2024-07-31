package com.VooTreeVeeVuu.usecase.UserUseCase.UpdateUser;

import com.VooTreeVeeVuu.dto.UserDTO;
import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class UpdateUserImpl implements UpdateUser{
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Optional<UserDTO> updateUser (Long id, UserDTO userDTO) {
		return userRepository.findById(id).map(user -> {
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setDob(LocalDate.parse(userDTO.getDob()));
			user.setGender(userDTO.getGender());
			User updated = userRepository.save(user);
			return toDTO(updated);
		});
	}

	private UserDTO toDTO (User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setDob(String.valueOf(user.getDob()));
		userDTO.setGender(user.getGender());
		return userDTO;
	}
}
