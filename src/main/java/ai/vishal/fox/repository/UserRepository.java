package ai.vishal.fox.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ai.vishal.fox.model.security.User;


public interface UserRepository extends MongoRepository<User,Integer>{
     Optional<User> findByUserName(String userName);
}
