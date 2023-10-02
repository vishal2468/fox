package ai.vishal.fox.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import ai.vishal.fox.model.request.AccessTokenRequestBody;
import ai.vishal.fox.model.request.RefreshTokenRequestBody;
import ai.vishal.fox.model.response.AccessTokenResponseBody;
import ai.vishal.fox.model.response.RefreshTokenResponseBody;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AccessController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "Hello, Home!";
    }

    /*
     * will use this endpoint to request access of their health data.
     * this is nothing to do with registering the user , it is for requesting the access.
     */
    @GetMapping("/google/auth")
    public RedirectView getMethodName() {
        String redirectUrl = "https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=https%3A//www.googleapis.com/auth/fitness.blood_pressure.read&" +
                "access_type=offline&" +
                "include_granted_scopes=true&" +
                "response_type=code&" +
                "state=there&" +
                "redirect_uri=http%3A//localhost%3A8080/code&" +
                "client_id=511506999617-u661nv4d9ih94ii7cd27nn23vfgpc46q.apps.googleusercontent.com";
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }
    /*
     * redirect url that will accept a short lived code from the google outh server as callback
     * we can then use this code to get refresh token
     * refresh token is received only when the user register for the first time
     */
    @GetMapping("/code")
    public RefreshTokenResponseBody callback(@RequestParam String code) {
        String url = "https://oauth2.googleapis.com/token";
        RefreshTokenRequestBody refreshTokenRequestBody = new RefreshTokenRequestBody(code,
                    "511506999617-u661nv4d9ih94ii7cd27nn23vfgpc46q.apps.googleusercontent.com",
                    "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", 
                    "http://localhost:8080/code", 
                    "authorization_code");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RefreshTokenRequestBody> requestEntity = new HttpEntity<>(refreshTokenRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, RefreshTokenResponseBody.class).getBody();
    }

    /*
     * We use this api to get access token from refresh token
     */

    @GetMapping("/accesstoken")
    public AccessTokenResponseBody getAccessToken(@RequestParam String refreshToken) {
        String url = "https://oauth2.googleapis.com/token";
        AccessTokenRequestBody accessTokenRequestBody = new AccessTokenRequestBody(
                    "511506999617-u661nv4d9ih94ii7cd27nn23vfgpc46q.apps.googleusercontent.com",
                    "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", 
                    refreshToken,
                    "refresh_token");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccessTokenRequestBody> requestEntity = new HttpEntity<>(accessTokenRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, AccessTokenResponseBody.class).getBody();
    }

}