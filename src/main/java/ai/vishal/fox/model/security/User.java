package ai.vishal.fox.model.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document
public class User {
    @Id
    private final int id;
    @Field("user_name")
    private final String userName;
    private final String password;
    private final boolean active;
    private final String roles;
    private final String email;
    @Field("from_phone_number_id")
    private final String fromPhoneNumberId;
    @Field("access_token")
    private final String accessToken;

}
