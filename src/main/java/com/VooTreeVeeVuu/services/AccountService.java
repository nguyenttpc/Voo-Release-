package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.domain.repository.RoleRepository;
import com.VooTreeVeeVuu.dto.PassChangeDTO;
import com.VooTreeVeeVuu.domain.entity.Account;
import com.VooTreeVeeVuu.domain.entity.OTP;
import com.VooTreeVeeVuu.domain.repository.AccountRepository;
import com.VooTreeVeeVuu.domain.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
	private static final long OTP_EXPIRATION_MINUTES = 1;

	private final String uploadDir = "/Users/aaronnguyen/eclipse-workspace/VooTreeVeeVuu/uploads/";

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private OTPRepository otpRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private JwtUtils jwtUtils;

	private String generateOTP () {
		return String.valueOf((int) (Math.random() * 900000) + 100000);
	}

	public void generateAndSendOTP (String email) {
		Account account = accountRepository.findByEmail(email);
		if (account == null)
		{
			throw new IllegalArgumentException("Invalid email");
		}

		//remove existed otp in account
		otpRepository.deleteByAccount(account);

		//generate new otp
		String otp = generateOTP();
		OTP otpToken = new OTP();
		otpToken.setOtp(otp);
		otpToken.setGeneratedTime(LocalDateTime.now());
		otpToken.setAccount(account);
		otpRepository.save(otpToken);

		emailService.sendOTP(email, otp);
	}

	public boolean isExpired (OTP otp) {
		return otp.getGeneratedTime().plusMinutes(OTP_EXPIRATION_MINUTES).isBefore(LocalDateTime.now());
	}

	public void resetPassword (String email, String otp, String newPassword) {
		Account account = accountRepository.findByEmail(email);
		if (account == null)
		{
			throw new IllegalArgumentException("Invalid email");
		}
		OTP otpToken = otpRepository.findByAccount(account);
		if (otpToken == null)
		{
			throw new IllegalArgumentException("No OTP for this account");
		}
		if (isExpired(otpToken))
		{
			otpRepository.delete(otpToken);
			throw new IllegalArgumentException("OTP has expired");
		}
		if (!otp.equals(otpToken.getOtp()))
		{
			throw new IllegalArgumentException("Invalid OTP");
		}
		//change password
		if (passwordEncoder.matches(account.getPassword(), newPassword))
		{
			System.out.println(account.getPassword());
			throw new IllegalArgumentException("Must be different from old password");
		}
		account.setPassword(passwordEncoder.encode(newPassword));
		accountRepository.save(account);
		//clean used otp
		otpRepository.delete(otpToken);
	}

	public void changePassword (Long id, PassChangeDTO passChangeDTO) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(passChangeDTO.getOldPassword(), account.getPassword()))
		{
			throw new RuntimeException("Old password is incorrect");
		}

		if (passwordEncoder.matches(passChangeDTO.getNewPassword(), account.getPassword()))
		{
			throw new RuntimeException("Can't use same old password");
		}

		if (!passChangeDTO.getNewPassword().equals(passChangeDTO.getConfPassword()))
		{
			throw new RuntimeException("Confirm password does not match!");
		}

		account.setPassword(passwordEncoder.encode(passChangeDTO.getNewPassword()));
		accountRepository.save(account);
	}

	public void requestEmailChange (String curEmail, String newEmail) {
		Account account = accountRepository.findByEmail(curEmail);

		if (account == null)
		{
			throw new IllegalArgumentException("Current email not found");
		}
		if (accountRepository.findByEmail(newEmail) != null)
		{
			throw new IllegalArgumentException("New email is already in use");
		}

		// Remove existing OTP for the new email if any
		otpRepository.deleteByAccountAndNewEmail(account, newEmail);

		// Generate new OTP
		String otp = generateOTP();
		OTP otpToken = new OTP();
		otpToken.setOtp(otp);
		otpToken.setGeneratedTime(LocalDateTime.now());
		otpToken.setAccount(account);
		otpToken.setNewEmail(newEmail);
		otpRepository.save(otpToken);

		emailService.sendOTP(newEmail, otp);
	}

	public void requestPhoneNumChange (String email, String curPhoneNum, String newPhone) {
		Account account = accountRepository.findByEmail(email);
		if (account == null)
		{
			throw new IllegalArgumentException("User not found with the provided email.");
		}
		if (accountRepository.findByPhoneNum(curPhoneNum) == null)
		{
			throw new IllegalArgumentException("User not found with this phone number");
		}
		if (accountRepository.findByPhoneNum(newPhone) != null)
		{
			throw new IllegalArgumentException("New phone number is currently in use");
		}

		// Remove existing OTP for the new phone number if any
		otpRepository.deleteByNewEmailAndNewPhoneNum(email, newPhone);

		// Generate new OTP
		String otp = generateOTP();
		OTP otpToken = new OTP();
		otpToken.setOtp(otp);
		otpToken.setGeneratedTime(LocalDateTime.now());
		otpToken.setAccount(account);
		otpToken.setNewEmail(email);
		otpToken.setNewPhoneNum(newPhone);
		otpRepository.save(otpToken);

		emailService.sendOTP(email, otp);
	}

	public void verifyAndChangeEmail (String curEmail, String otp, String newEmail) {
		Account account = accountRepository.findByEmail(curEmail);
		if (account == null)
		{
			throw new IllegalArgumentException("Current email not found");
		}
		OTP otpToken = otpRepository.findByAccountAndNewEmail(account, newEmail);
		if (otpToken == null)
		{
			throw new IllegalArgumentException("OTP not generate for this user or new email");
		}
		if (isExpired(otpToken))
		{
			//delete expired otp
			otpRepository.delete(otpToken);
			throw new IllegalArgumentException("OTP has expired");
		}
		if (!otp.equals(otpToken.getOtp()))
		{
			throw new IllegalArgumentException("Invalid OTP");
		}

		//Update mail
		account.setEmail(newEmail);
		accountRepository.save(account);
		otpRepository.delete(otpToken);
	}

	public void verifyAndChangePhoneNum (String email, String otp, String newPhoneNum) {
		Account account = accountRepository.findByEmail(email);
		if (account == null)
		{
			throw new IllegalArgumentException("User not found");
		}

		OTP otpToken = otpRepository.findByNewEmailAndNewPhoneNum(email, newPhoneNum);
		if (otpToken == null)
		{
			throw new IllegalArgumentException("OTP not generated for this user or new phone number");
		}
		if (isExpired(otpToken))
		{
			otpRepository.delete(otpToken); // Clean up expired OTP
			throw new IllegalArgumentException("OTP has expired");
		}
		if (!otp.equals(otpToken.getOtp()))
		{
			throw new IllegalArgumentException("Invalid OTP");
		}

		// Update the phone number
		account.setPhoneNum(newPhoneNum);
		accountRepository.save(account);
		otpRepository.delete(otpToken); // Clean up used OTP
	}

	public void updateAvatar (Long id, MultipartFile file) throws IOException {
		Optional<Account> optionalAccount = accountRepository.findById(id);
		if (optionalAccount.isPresent())
		{
			Account account = optionalAccount.get();
			account.setAvatar(file.getBytes());
			accountRepository.save(account);
		} else
		{
			throw new RuntimeException("User not found");
		}
	}

	public byte[] getAvatar (Long id) {
		Optional<Account> optionalAccount = accountRepository.findById(id);
		if (optionalAccount.isPresent() && optionalAccount.get().getAvatar() != null)
		{
			return optionalAccount.get().getAvatar();
		} else
		{
			throw new RuntimeException("Avatar not found");
		}
	}


}
