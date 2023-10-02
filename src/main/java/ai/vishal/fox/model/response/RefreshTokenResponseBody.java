package ai.vishal.fox.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class RefreshTokenResponseBody {
    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("expires_in")
    public long expiresIn;
    @JsonProperty("token_type")
    public String tokenType;
    @JsonProperty("scope")
    public String scope;
    @JsonProperty("refresh_token")
    public String refresToken;
}
