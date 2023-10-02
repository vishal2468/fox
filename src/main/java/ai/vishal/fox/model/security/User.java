package ai.vishal.fox.model.security;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document
@JsonIgnoreProperties
public class User {
    @Id
    private String userName;
    private String password;
    private boolean active;
    private String roles;
    private String email;
    private String accessToken;

}
