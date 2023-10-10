package ai.vishal.fox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ai.vishal.fox.model.request.AccessTokenRequestBody;
import ai.vishal.fox.model.response.AccessTokenResponseBody;

@Service
public class AccessService {

    @Autowired
    RestTemplate restTemplate;

    public String getAccessToken(String refreshToken){
        
        String url = "https://oauth2.googleapis.com/token";
        AccessTokenRequestBody accessTokenRequestBody = new AccessTokenRequestBody(
                    "511506999617-u661nv4d9ih94ii7cd27nn23vfgpc46q.apps.googleusercontent.com",
                    "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", 
                    refreshToken,
                    "refresh_token");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccessTokenRequestBody> requestEntity = new HttpEntity<>(accessTokenRequestBody, httpHeaders);
        AccessTokenResponseBody accessTokenResponseBody=  restTemplate.exchange(url, HttpMethod.POST, requestEntity, AccessTokenResponseBody.class).getBody();
        return accessTokenResponseBody.accessToken;
    }
}
