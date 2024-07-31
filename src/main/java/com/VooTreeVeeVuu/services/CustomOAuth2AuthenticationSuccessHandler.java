package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.domain.entity.Account;
import com.VooTreeVeeVuu.domain.entity.Role;
import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.repository.AccountRepository;
import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import com.VooTreeVeeVuu.domain.repository.UserRepository;
import com.VooTreeVeeVuu.domain.utils.RoleName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private final JwtUtils jwtUtils;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public CustomOAuth2AuthenticationSuccessHandler (JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	@Override
	public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response,
	                                     Authentication authentication) throws IOException {
		// Retrieve OAuth2 user details
		String email = null;

		Object principal = authentication.getPrincipal();
		if (principal instanceof OAuth2User oauth2User)
		{
			email = oauth2User.getAttribute("email");
		} else if (principal instanceof OidcUser)
		{
			OidcUser oidcUser = (OidcUser) principal;
			email = oidcUser.getEmail();
		}

		if (email == null)
		{
			throw new RuntimeException("Email not found in OAuth2 principal");
		}

		// Fetch or create the Account entity based on email
		Account account = saveOrUpdateAccount(email);

		String token = JwtUtils.generateToken(account);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"token\": \"" + token + "\"}");
		response.getWriter().flush(); // Flush the writer to ensure all data is written
	}

	@Transactional
	public Account saveOrUpdateAccount (String email) {
		// Check if the account already exists in the database
		Account account = accountRepository.findByEmail(email);

		if (account == null)
		{
			// Create a new Account entity if it doesn't exist
			account = new Account();
			account.setEmail(email);
			account.setUsername(email); // Set username as email
			account.setPassword(""); // Set a default or temporary password

			// Fetch existing roles from the database
			Set<Role> roles = new HashSet<>();
			Optional<Role> customerRole = roleRepository.findByName(RoleName.CUSTOMER);

			// Add existing role if found, otherwise handle as needed
			customerRole.ifPresent(roles :: add);
			account.setRoles(roles);

			// Save the Account entity first
			account = accountRepository.save(account);

			// Create a new User entity associated with the Account
			User user = new User();
			user.setAccount(account); // Set the Account for the User

			// Save the User entity
			user = userRepository.save(user);

			// Update the Account with the User
			account.setUser(user);
			account = accountRepository.save(account);
		} else
		{
			// Update existing Account entity and associated User if needed
			// For simplicity, assuming updates are handled as per your business logic
			// Update user's details if needed
			User user = account.getUser();
			if (user == null)
			{
				user = new User();
				user.setAccount(account); // Set the Account for the User
				user = userRepository.save(user);
				account.setUser(user);
			}
			// Save the updated entities
			account = accountRepository.save(account);
		}

		return account;
	}

}
