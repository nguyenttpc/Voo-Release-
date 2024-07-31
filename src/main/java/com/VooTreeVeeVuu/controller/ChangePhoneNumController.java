package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin ("*")
@RestController
@RequestMapping ("/api/phone")
public class ChangePhoneNumController {
	@Autowired
	private AccountService accountService;

	@PostMapping ("/change-request")
	public ResponseEntity<String> requestPhoneNumChange (@RequestParam String email, @RequestParam String curPhoneNum,
	                                                     @RequestParam String newPhoneNum) {
		try
		{
			accountService.requestPhoneNumChange(email, curPhoneNum, newPhoneNum);
			return ResponseEntity.ok("OTP sent to your email");
		} catch (IllegalArgumentException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping ("/verify")
	public ResponseEntity<String> verifyAndChangePhoneNum (@RequestParam String email, @RequestParam String otp,
	                                                       @RequestParam String newPhoneNum) {
		try
		{
			accountService.verifyAndChangePhoneNum(email, otp, newPhoneNum);
			return ResponseEntity.ok("Phone number has been changed successfully");
		} catch (IllegalArgumentException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
