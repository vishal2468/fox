package ai.vishal.fox.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestBody {
    @JsonProperty("code")
    String code;
    @JsonProperty("client_id")
    String clientId;
    @JsonProperty("client_secret")
    String clientSecret;
    @JsonProperty("redirect_uri")
    String redirectUri;
    @JsonProperty("grant_type")
    String grantType;
}
