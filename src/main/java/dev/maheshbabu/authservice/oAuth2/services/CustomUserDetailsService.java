package dev.maheshbabu.authservice.oAuth2.services;

import dev.maheshbabu.authservice.models.User;
import dev.maheshbabu.authservice.oAuth2.models.CustomUserDetails;
import dev.maheshbabu.authservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       User user =  userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new CustomUserDetails(user);
    }
}
