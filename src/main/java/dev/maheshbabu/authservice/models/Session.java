package dev.maheshbabu.authservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseModel{

    @ManyToOne
    private User user;
    private String token;
    private Date expired;
    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
}
