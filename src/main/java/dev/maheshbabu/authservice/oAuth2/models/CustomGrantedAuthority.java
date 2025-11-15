package dev.maheshbabu.authservice.oAuth2.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.maheshbabu.authservice.models.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

   // private final Role role;
    private String authority;

    public CustomGrantedAuthority(Role role) {
       // this.role = role;
        this.authority = role.getName();
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
