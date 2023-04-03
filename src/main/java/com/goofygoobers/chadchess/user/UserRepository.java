package com.goofygoobers.chadchess.user;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import com.goofygoobers.chadchess.user.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Primary
public interface UserRepository extends CrudRepository<User, Integer> {

}
