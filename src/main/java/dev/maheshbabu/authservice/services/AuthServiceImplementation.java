package dev.maheshbabu.authservice.services;

import dev.maheshbabu.authservice.exceptions.IncorrectPasswordException;
import dev.maheshbabu.authservice.exceptions.InvalidSessionException;
import dev.maheshbabu.authservice.exceptions.UserAlreadyExistsException;
import dev.maheshbabu.authservice.exceptions.UserNotFoundException;
import dev.maheshbabu.authservice.models.Session;
import dev.maheshbabu.authservice.models.SessionStatus;
import dev.maheshbabu.authservice.models.User;
import dev.maheshbabu.authservice.repositories.SessionRepository;
import dev.maheshbabu.authservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.*;

import java.util.Optional;

@Service
public class AuthServiceImplementation implements AuthService {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public AuthServiceImplementation(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public User login(String email, String password) throws Exception {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = optionalUser.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(bCryptPasswordEncoder.matches(password,user.getPassword())){
            Session session = new Session();
            session.setUser(user);
            session.setToken(RandomStringUtils.randomAscii(20));
            session.setSessionStatus(SessionStatus.ACTIVE);
            sessionRepository.save(session);
            return user;
        }else {
            throw new IncorrectPasswordException("Incorrect password");
        }

    }

    @Override
    public User signup(String email, String password) throws Exception {

       Optional<User> optionalUser =  userRepository.findByEmail(email);
       if(optionalUser.isPresent()){
           throw new UserAlreadyExistsException("User already exists with email: " + email);
       }

       User user = new User();
       user.setEmail(email);
       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       user.setPassword(bCryptPasswordEncoder.encode(password));

       userRepository.save(user);
       return user;
    }

    @Override
    public String logout(String email, String token) throws Exception {

       Optional<User> optionalUser = userRepository.findByEmail(email);

       if(optionalUser.isEmpty()) {
           throw new UserNotFoundException("User not found");
       }

       Optional<Session> optionalSession = sessionRepository.findByUserAndToken(optionalUser.get(), token);

       if(optionalSession.isEmpty()) {
           throw new InvalidSessionException("Invalid session");
       }

       Session session = optionalSession.get();

       session.setSessionStatus(SessionStatus.LOGGED_OUT);

       sessionRepository.save(session);

        return "Successfully logged out";
    }


    @Override
    public User setRole(String email, String role) throws Exception {
        return null;
    }

    @Override
    public boolean validate(long userId, String token) throws Exception {

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()) {
           return false;
        }

        Optional<Session> optionalSession = sessionRepository.findByUserAndToken(optionalUser.get(), token);

        if(optionalSession.isEmpty()){
            return false;
        }else {
            return optionalSession.get().getSessionStatus() == SessionStatus.ACTIVE;
        }
    }
}
