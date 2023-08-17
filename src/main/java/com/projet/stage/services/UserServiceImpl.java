package com.projet.stage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.stage.DTO.LoginDTO;
import com.projet.stage.DTO.UserDTO;
import com.projet.stage.entities.User;
import com.projet.stage.repos.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import com.projet.stage.DTO.LoginMesage;

@Service
public class UserServiceImpl implements UserService ,UserDetailsService {
    @Autowired
private UserRepository userrepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
	@Override
	public String addUser(UserDTO userDTO) {
      
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userrepo.save(user);

        return user.getUsername();
    }	@Override
	public LoginMesage loginUser(LoginDTO logindto) {
		// TODO Auto-generated method stub
		 String msg = "";
	        User employee1 = userrepo.findByEmail(logindto.getEmail());
	        if (employee1 != null) {
	            String password = logindto.getPassword();
	            String encodedPassword = employee1.getPassword();
	            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
	            if (isPwdRight) {
	                Optional<User> employee = userrepo.findOneByEmailAndPassword(logindto.getEmail(), encodedPassword);
	                if (employee.isPresent()) {
	                    return new LoginMesage("Login Success", true);
	                } else {
	                    return new LoginMesage("Login Failed", false);
	                }
	            } else {
	                return new LoginMesage("password Not Match", false);
	            }
	        }else {
	            return new LoginMesage("Email not exits", false);
	        }
	    }
	  @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userrepo.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with email: " + email);
	        }
	        // Créez et renvoyez un objet UserDetails ici
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	    }
	

}
