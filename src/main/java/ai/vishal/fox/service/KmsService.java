package ai.vishal.fox.service;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ai.vishal.fox.model.dto.AggregateBy;
import ai.vishal.fox.model.dto.BucketByTime;
import ai.vishal.fox.model.request.CipherRequest;
import ai.vishal.fox.model.request.StatsRequestBody;
import ai.vishal.fox.model.response.CipherResponse;

@Service
public class KmsService {

    @Autowired
    RestTemplate restTemplate;

    public String encrypt(String refreshToken) {
        refreshToken=Base64.getEncoder().encodeToString(refreshToken.getBytes());
        String url="https://KSM-endpoint";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setBearerAuth("Bearer token ");
        CipherRequest cipherRequest=new CipherRequest(refreshToken);
        
        HttpEntity<CipherRequest> requestEntity = new HttpEntity<>(cipherRequest, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, CipherResponse.class).getBody().getData();
    }

    public String decrypt(String encryptedRefreshToken) {
        String url="https://KSM-endpoint";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setBearerAuth("Bearer token ");
        CipherRequest cipherRequest=new CipherRequest(encryptedRefreshToken);
        
        HttpEntity<CipherRequest> requestEntity = new HttpEntity<>(cipherRequest, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, CipherResponse.class).getBody().getData();
    }
    
}
