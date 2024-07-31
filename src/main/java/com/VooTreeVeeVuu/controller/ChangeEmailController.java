package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin ("*")
@RestController
@RequestMapping ("/api/email")
public class ChangeEmailController {
	@Autowired
	private AccountService accountService;

	@PostMapping ("/change-request")
	public ResponseEntity<String> requestEmailChange (@RequestParam String curEmail, @RequestParam String newEmail) {
		try
		{
			accountService.requestEmailChange(curEmail, newEmail);
			return ResponseEntity.ok("OTP sent to your new email");
		} catch (IllegalArgumentException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping ("/verify")
	public ResponseEntity<String> verifyAndChangeEmail (@RequestParam String curEmail, @RequestParam String otp,
	                                                    @RequestParam String newEmail) {
		try
		{
			accountService.verifyAndChangeEmail(curEmail, otp, newEmail);
			return ResponseEntity.ok("Email has been changed successfully");
		} catch (IllegalArgumentException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
