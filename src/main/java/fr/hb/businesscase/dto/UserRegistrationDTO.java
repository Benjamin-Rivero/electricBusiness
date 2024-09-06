package fr.hb.businesscase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private LocalDate birthDate;
    private AddressRegistrationDTO addressDto;

}
