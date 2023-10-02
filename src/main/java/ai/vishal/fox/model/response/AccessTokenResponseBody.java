package ai.vishal.fox.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class AccessTokenResponseBody {
    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("expires_in")
    public int expiresIn;
    @JsonProperty("scope")
    public String scope;
    @JsonProperty("token_type")
    public String tokenType;
}
