package dev.maheshbabu.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String errorCode;
    private String errorMessage;
}
