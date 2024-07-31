package com.VooTreeVeeVuu.domain.entity;

import com.VooTreeVeeVuu.domain.utils.Action;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "logs")
public class Logs {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (name = "action", columnDefinition = "nvarchar(50)")
	@Enumerated (EnumType.STRING)
	private Action action;

	@DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
	@Temporal (TemporalType.DATE)
	@Column (name = "actionDate")
	private LocalDate date;

	//@JsonIgnored
	@ManyToOne
	@JoinColumn (name = "hotelId")
	Hotel hotel;
	
	//@JsonIgnored
	@ManyToOne
	@JoinColumn (name = "staffId")
	User user;


}
