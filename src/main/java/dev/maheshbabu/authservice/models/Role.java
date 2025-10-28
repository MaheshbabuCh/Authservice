package dev.maheshbabu.authservice.models;

import jakarta.persistence.Entity;

@Entity
public class Role extends BaseModel {
    private String name;
}
