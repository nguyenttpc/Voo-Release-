package com.VooTreeVeeVuu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    //private LocalDate dob;
    private String firstName;
    private String lastName;
    private Set<String> roles;

    private boolean status = true;

}
