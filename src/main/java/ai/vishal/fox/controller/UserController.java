package ai.vishal.fox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ai.vishal.fox.model.security.User;
import ai.vishal.fox.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/signup")
    public void userSignup(@RequestBody User user) {
        userRepository.save(user);
    }
}
