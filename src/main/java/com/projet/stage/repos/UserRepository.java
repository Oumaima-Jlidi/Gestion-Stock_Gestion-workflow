package com.projet.stage.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.projet.stage.entities.User;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository  extends JpaRepository<User ,Integer>{
	Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findByUsername(String username);

}
