package dev.maheshbabu.authservice.controllers;

import dev.maheshbabu.authservice.dtos.LoginRequestDto;
import dev.maheshbabu.authservice.dtos.LoginResponseDto;
import dev.maheshbabu.authservice.dtos.SignupRequestDto;
import dev.maheshbabu.authservice.dtos.SignupResponseDto;
import dev.maheshbabu.authservice.exceptions.IncorrectPasswordException;
import dev.maheshbabu.authservice.exceptions.UserAlreadyExistsException;
import dev.maheshbabu.authservice.exceptions.UserNotFoundException;
import dev.maheshbabu.authservice.models.User;
import dev.maheshbabu.authservice.repositories.UserRepository;
import dev.maheshbabu.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    //private final UserRepository userRepository;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
       // this.userRepository = userRepository;
    }


    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody  SignupRequestDto signupRequestDto) throws Exception {

        String email = signupRequestDto.getEmail();
        String password = signupRequestDto.getPassword();
        try {
            User user = userService.signup(email, password);
            SignupResponseDto signupResponseDto = new SignupResponseDto();
            signupResponseDto.setEmail(user.getEmail());
            signupResponseDto.setMessage("User registered successfully");
            return new ResponseEntity<>(signupResponseDto, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        try {
          User user =  userService.login(email, password);
          LoginResponseDto loginResponseDto = new LoginResponseDto();
          loginResponseDto.setEmail(user.getEmail());
          return ResponseEntity.ok(loginResponseDto);

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (IncorrectPasswordException e) {
            throw new IncorrectPasswordException(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponseDto> logout(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        return null;

    }

    @PostMapping("/role")
    public ResponseEntity<LoginResponseDto> setRole(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        return null;

    }

}
