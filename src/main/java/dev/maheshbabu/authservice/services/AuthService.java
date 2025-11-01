package dev.maheshbabu.authservice.services;

import dev.maheshbabu.authservice.models.User;

public interface AuthService {

    public User login(String email, String password) throws Exception;

    public User signup(String email, String password) throws Exception;

    public String logout(String email, String token) throws Exception;

    public User setRole(String email, String role) throws Exception;


}
