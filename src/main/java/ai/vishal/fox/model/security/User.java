package ai.vishal.fox.model.security;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document
@JsonInclude(Include.NON_NULL)
public class User {
    @Id
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean active;
    private String roles;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String refreshToken;
    private Set<String> accessiblePatients;

}
