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

    @Value("${mek}")
    private String mek;

    private boolean useKms = false;

    public String encrypt(String refreshToken) {
        if(!useKms) return refreshToken;
        refreshToken=Base64.getEncoder().encodeToString(refreshToken.getBytes());
        String url="https://qa.otkm.bp.anthos.otxlab.net/api/meks/"+mek+"/encrypt";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("x-api-key", devEnc);
        CipherRequest cipherRequest=new CipherRequest(refreshToken);
        
        HttpEntity<CipherRequest> requestEntity = new HttpEntity<>(cipherRequest, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, CipherResponse.class).getBody().getData();
    }

    public String decrypt(String encryptedRefreshToken) {
        if(!useKms) return encryptedRefreshToken;
        String url="https://qa.otkm.bp.anthos.otxlab.net/api/meks/"+mek+"/decrypt";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("x-api-key", devDec);
        CipherRequest cipherRequest=new CipherRequest(encryptedRefreshToken);
        
        HttpEntity<CipherRequest> requestEntity = new HttpEntity<>(cipherRequest, httpHeaders);
        return new String(Base64.getDecoder().decode(restTemplate.exchange(url, HttpMethod.POST, requestEntity, CipherResponse.class).getBody().getData()));
    }
    
}
