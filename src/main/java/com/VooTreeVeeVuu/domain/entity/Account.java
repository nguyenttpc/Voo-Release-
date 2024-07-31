package com.VooTreeVeeVuu.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "Accounts")

public class Account implements UserDetails {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column (unique = true, nullable = false)
	private String username;

	private String password;

	@Email
	@Column (unique = true, nullable = false)
	private String email;

	@Column(unique = true)
	private String phoneNum;

	@Lob
	private byte[] avatar;

	private boolean status = true;

	@JsonIgnore
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn (name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable (name = "account_roles", joinColumns = @JoinColumn (name = "account_id"), inverseJoinColumns = @JoinColumn (name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@OneToMany (mappedBy = "account")
	private List<OTP> otpList;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString())).collect(
				Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired () {
		return true;
	}

	@Override
	public boolean isAccountNonLocked () {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired () {
		return true;
	}

	@Override
	public boolean isEnabled () {
		return status;
	}

	public void setUser (User user) {
		this.user = user;
		user.setAccount(this);
	}
}
