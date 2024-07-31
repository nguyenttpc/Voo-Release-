package com.VooTreeVeeVuu.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "otp")
public class OTP {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String otp;
	private LocalDateTime generatedTime;
	private String newEmail;
	private String newPhoneNum;

	@ManyToOne
	@JoinColumn (name = "account_id", nullable = false)
	private Account account;
}
