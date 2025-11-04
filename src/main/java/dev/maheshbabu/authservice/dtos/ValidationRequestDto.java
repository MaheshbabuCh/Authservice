package dev.maheshbabu.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationRequestDto {

    private long userId;
    private String token;

}
