package com.workshopmongo.workshopmongo.repository;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.workshopmongo.workshopmongo.domain.User;

@Repository // ou @Component
public interface UserRepository extends MongoRepository<User, String>{

}
