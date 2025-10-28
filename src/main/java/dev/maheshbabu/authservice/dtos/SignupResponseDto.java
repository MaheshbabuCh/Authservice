package dev.maheshbabu.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private String email;
    private String message;
}
