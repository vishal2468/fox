package ai.vishal.fox.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/user/{username}")
    public Optional<User> findByUserName(@PathVariable("username") String username){
        Optional<User> user= userRepository.findByUserName(username);
        return user;
    }

}
