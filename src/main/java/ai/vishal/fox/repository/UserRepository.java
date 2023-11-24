package ai.vishal.fox.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ai.vishal.fox.model.security.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
    Optional<User> findByUserName(String userName);

    List<User> findByRoles(String string);
}
