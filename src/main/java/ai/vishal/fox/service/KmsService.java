package ai.vishal.fox.service;

import org.springframework.stereotype.Service;

@Service
public class KmsService {

    public String encrypt(String refreshToken) {
        return refreshToken;
    }

    public String decrypt(String encryptedAccessToken) {
        return encryptedAccessToken;
    }
    
}
