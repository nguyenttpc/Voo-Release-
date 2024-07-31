package com.VooTreeVeeVuu.domain.repository;

import com.VooTreeVeeVuu.domain.entity.Account;
import com.VooTreeVeeVuu.domain.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
	OTP findByAccount (Account account);

	OTP findByAccountAndNewEmail (Account account, String newEmail);

	OTP findByNewEmailAndNewPhoneNum(String userEmail, String newPhoneNum);

	@Transactional
	void deleteByNewEmailAndNewPhoneNum(String userEmail, String newPhoneNum);

	@Transactional
	void deleteByAccount (Account account);

	@Transactional
	void deleteByAccountAndNewEmail (Account account, String newEmail);
}