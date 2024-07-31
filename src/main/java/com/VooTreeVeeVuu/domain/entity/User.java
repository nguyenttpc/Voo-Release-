package com.VooTreeVeeVuu.domain.entity;


import com.VooTreeVeeVuu.domain.utils.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", columnDefinition = "nvarchar(50)")
    private String firstName;
    @Column(name = "lastName", columnDefinition = "nvarchar(200)")
    private String lastName;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Temporal(TemporalType.DATE)
    private LocalDate dob;
    @JsonIgnore
    @Setter
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Payment> payments;

    public User(Long userId) {
        this.id = userId;
    }


    public User(Long id, String firstName, String lastName, Gender gender, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
    }

    public User (String name) {
        this.lastName = name;
    }
}
