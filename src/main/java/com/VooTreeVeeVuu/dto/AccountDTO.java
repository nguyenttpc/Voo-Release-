package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	private Long id;
	private String username;
	private String password;
	private String email;
	private String phoneNum;
	private Set<Role> roles;
	private boolean enabled;
	private User user;
}
