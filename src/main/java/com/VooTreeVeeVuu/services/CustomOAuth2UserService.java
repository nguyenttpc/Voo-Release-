package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.domain.entity.Account;
import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.repository.AccountRepository;
import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import com.VooTreeVeeVuu.domain.repository.UserRepository;
import com.VooTreeVeeVuu.domain.utils.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends OidcUserService {
	private final AccountRepository accountRepository;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public Account saveOrUpdateAccount (String email) {
		// Check if the account already exists in the database
		Account account = accountRepository.findByEmail(email);

		if (account == null)
		{
			// Create a new Account entity if it doesn't exist
			account = new Account();
			account.setEmail(email);
			account.setUsername(email); // Set username as email
			account.setPassword("");

			Set<Role> roles = new HashSet<>();
			Optional<Role> customerRole = roleRepository.findByName(RoleName.CUSTOMER);

			// Add existing role if found, otherwise handle as needed
			customerRole.ifPresent(roles :: add);

			account.setRoles(roles);

			// Create a new User entity associated with the Account
			User user = new User();

			// Set the User for the Account
			user.setAccount(account); // Set the Account for the User

			// Save the User and Account entities
			userRepository.save(user);
			account.setUser(user);
			accountRepository.save(account);
		} else
		{
			// Update existing Account entity and associated User if needed
			// For simplicity, assuming updates are handled as per your business logic
			// Update user's details if needed
			User user = account.getUser();
			if (user == null)
			{
				user = new User();
				user.setAccount(account);
				userRepository.save(user);
				account.setUser(user);// Set the Account for the User
			}
			// Save the updated entities
			accountRepository.save(account);
			//userRepository.save(user);
		}
		return account;
	}

	@Override
	public OidcUser loadUser (OidcUserRequest userRequest) {
		OAuth2UserService<OidcUserRequest, OidcUser> delegate = new OidcUserService();
		OidcUser oidcUser = delegate.loadUser(userRequest);

		// Extract user details from the OIDC user
		String email = oidcUser.getAttribute("email");

		// Create or update the Account entity
		Account account = saveOrUpdateAccount(email);

		// Return an OidcUser representing the authenticated user
		return oidcUser;
	}

}
