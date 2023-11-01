package ai.vishal.fox.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ai.vishal.fox.model.request.CipherRequest;
import ai.vishal.fox.model.response.CipherResponse;

@Service
public class KmsService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${user.devenc}")
    private String devEnc;

    @Value("${user.devdec}")
    private String devDec;

    @Value("$mek")
    private String mek;
    
    public String encrypt(String refreshToken) {
        refreshToken=Base64.getEncoder().encodeToString(refreshToken.getBytes());
        String url="https://KSM-endpoint";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("x-api-key", devEnc);
        CipherRequest cipherRequest=new CipherRequest(refreshToken);
        
        HttpEntity<CipherRequest> requestEntity = new HttpEntity<>(cipherRequest, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, CipherResponse.class).getBody().getData();
    }

    public String decrypt(String encryptedRefreshToken) {
        String url="https://KSM-endpoint";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("x-api-key", devDec);
        CipherRequest cipherRequest=new CipherRequest(encryptedRefreshToken);
        
        HttpEntity<CipherRequest> requestEntity = new HttpEntity<>(cipherRequest, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, CipherResponse.class).getBody().getData();
    }
    
}
