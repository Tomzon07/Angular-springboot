
package com.innovaturelabs.training.examportal.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.innovaturelabs.training.examportal.entity.User;


public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(Integer userId);

    Optional<User> findByUserIdAndPassword(Integer userId, String password);

    Optional<User> findByEmail(String email);

    User save(User user);
    
    Collection<User> findAllByRole(byte role);
    void delete(User user);

    @Query(value="select * from user where reset_password_token=?",nativeQuery=true)
	Optional<User> findByResetPasswordToken(String token);
	
	
    }
