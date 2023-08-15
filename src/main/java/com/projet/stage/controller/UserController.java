package com.projet.stage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.projet.stage.entities.User;

import com.projet.stage.DTO.LoginDTO;
import com.projet.stage.DTO.LoginMesage;
import com.projet.stage.DTO.UserDTO;
import com.projet.stage.repos.UserRepository;
import com.projet.stage.services.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/user")
public class UserController {
	 @Autowired
	 private UserService userservice;
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	 @PostMapping("/save")
	    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
	        String userid = userservice.addUser(userDTO);
	        return ResponseEntity.ok("User registered with ID: " + userid);
	 }
	        @PostMapping("/login")
	        public LoginMesage loginUser(@RequestBody LoginDTO loginDTO) {
	            return userservice.loginUser(loginDTO);
	        }
}
