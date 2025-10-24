package dev.maheshbabu.authservice.controllers;

import dev.maheshbabu.authservice.dtos.LoginRequestDto;
import dev.maheshbabu.authservice.dtos.LoginResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();





        return null;

    }

}
