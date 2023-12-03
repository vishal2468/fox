package ai.vishal.fox.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ai.vishal.fox.configuration.LoggedInUser;
import ai.vishal.fox.model.security.MyUserDetails;
import ai.vishal.fox.model.security.User;
import ai.vishal.fox.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/signup")
    public String userSignup(@RequestBody User user) {
        userRepository.save(user);
        return "signup successful";
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public Optional<User> findByUserName(@PathVariable String username) {
        return userRepository.findByUserName(username);
    }

    @GetMapping("/user/doctors")
    public List<User> findAllDoctors() {
        return userRepository.findByRoles("ROLE_DOCTOR");
    }

    @GetMapping("/user/patients")
    public List<User> findAllPatinets() {
        return userRepository.findByRoles("ROLE_PATIENT");
    }

    @GetMapping("/user/access/{doctor}")
    public String addPermittedDoctor(@PathVariable String doctor, @LoggedInUser MyUserDetails user) {
        if(user.getUser().getRoles().equals("ROLE_PATIENT")){
            Optional<User> d = userRepository.findById(doctor);
            if(d.isPresent()){
                if(d.get().getAccessiblePatients()==null) d.get().setAccessiblePatients(new HashSet<>());
                d.get().getAccessiblePatients().add(user.getUser().getUserName());
                userRepository.save(d.get());
            }
            else{
                return "Invalid doctor provided";
            }
        }
        return String.format("Doctor %s can access the data of Patient %s ",doctor , user.getUser().getUserName());
    }
}
