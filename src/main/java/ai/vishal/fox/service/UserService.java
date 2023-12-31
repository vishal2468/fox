package ai.vishal.fox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.vishal.fox.model.security.MyUserDetails;
import ai.vishal.fox.repository.UserRepository;



@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KmsService kmsService;

    public void saveUserRefreshToken(MyUserDetails userDetails,String refreshToken){
        userDetails.setRefreshToken(kmsService.encrypt(refreshToken));
        userRepository.save(userDetails.getUser());
    }
}
