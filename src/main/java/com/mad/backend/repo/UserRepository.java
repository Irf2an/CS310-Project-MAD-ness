package com.mad.backend.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mad.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findByEmail(String email);

}