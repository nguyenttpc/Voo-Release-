package com.VooTreeVeeVuu.domain.entity;


import com.VooTreeVeeVuu.domain.utils.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated (EnumType.STRING)
	private RoleName name;

	public Role (RoleName roleName) {
		this.name = roleName;
	}
}
