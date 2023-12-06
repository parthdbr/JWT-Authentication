package com.springrest.user.repository;

import com.springrest.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    User findByEmailContainingAndSoftDeleteIsFalse(String email);

    User findByIdAndSoftDeleteIsFalse(String id);

    List<User> findBySoftDeleteIsFalse();

    User findOneByEmailIgnoreCaseAndPasswordAndSoftDeleteIsFalse(String email, String password);


}
