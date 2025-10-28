package dev.maheshbabu.authservice.advices;

import dev.maheshbabu.authservice.dtos.ErrorDto;
import dev.maheshbabu.authservice.exceptions.IncorrectPasswordException;
import dev.maheshbabu.authservice.exceptions.UserAlreadyExistsException;
import dev.maheshbabu.authservice.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ErrorDto> userAlreadyExistsError(Exception e) {;
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("USER_ALREADY_EXISTS");
        errorDto.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ErrorDto> userNotFoundError(Exception e) {;
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("USER_NOT_FOUND");
        errorDto.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<ErrorDto> incorrectPasswordError(Exception e) {;
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("INCORRECT_PASSWORD");
        errorDto.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }


}
