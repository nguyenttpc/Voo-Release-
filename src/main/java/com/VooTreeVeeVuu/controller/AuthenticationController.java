package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.dto.AuthenticationResponse;
import com.VooTreeVeeVuu.dto.LoginDTO;
import com.VooTreeVeeVuu.dto.SignUpDTO;
import com.VooTreeVeeVuu.services.AuthenticationService;
import com.VooTreeVeeVuu.services.CustomOAuth2AuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin ("*")
@RestController
@RequestMapping ("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService service;

	private final CustomOAuth2AuthenticationSuccessHandler authenticationSuccessHandler;

	@PostMapping ("/register")
	public ResponseEntity<AuthenticationResponse> register (@RequestBody SignUpDTO request) {
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping ("/login")
	public ResponseEntity<AuthenticationResponse> login (@RequestBody LoginDTO request) {
		return ResponseEntity.ok(service.login(request));

	}

	@GetMapping("/success")
	public ResponseEntity<String> handleSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
		return ResponseEntity.ok("Authentication successful. Token generated and sent.");
	}

	@GetMapping("/failure")
	public ResponseEntity<String> handleFailure() {
		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Authentication failed.");
	}
}
