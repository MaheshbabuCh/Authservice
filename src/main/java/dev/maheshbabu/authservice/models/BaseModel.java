package dev.maheshbabu.authservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class BaseModel {

    long id;
    Date createdAt;
    Date updatedAt;
}
