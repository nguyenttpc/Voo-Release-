package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin ("*")
@RestController
@RequestMapping ("/api/password")
public class ResetPasswordController {
	@Autowired
	private AccountService accountService;

	@PostMapping ("/forgot")
	public ResponseEntity<String> forgotPassword (@RequestParam String email) {
		try
		{
			accountService.generateAndSendOTP(email);
			return ResponseEntity.ok("OTP has sent to your email");
		} catch (IllegalArgumentException exception)
		{
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}

	@PostMapping ("/reset")
	public ResponseEntity<String> resetPassword (@RequestParam String email, @RequestParam String otp,
	                                             @RequestParam String newPassword) {
		try
		{
			accountService.resetPassword(email, otp, newPassword);
			return ResponseEntity.ok("Password has been reset successfully");
		} catch (IllegalArgumentException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
