package dev.maheshbabu.authservice.services;

import dev.maheshbabu.authservice.models.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public User login(String email, String password) throws Exception;

    public User signup(String email, String password) throws Exception;

    public String logout(String email) throws Exception;

    public User setRole(String email, String role) throws Exception;


}
