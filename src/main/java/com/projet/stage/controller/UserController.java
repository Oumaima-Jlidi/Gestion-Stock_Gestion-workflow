package com.projet.stage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.projet.stage.DTO.AuthResponse;
import com.projet.stage.DTO.LoginDTO;
import com.projet.stage.DTO.UserDTO;
import com.projet.stage.repos.UserRepository;
import com.projet.stage.services.UserService;
import com.projet.stage.services.UserServiceImpl;

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
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private UserService userService;
	    @Autowired
	    private UserServiceImpl userimpl;
	    @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO authRequest) {
	        try {
	            authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
	            );
	        } catch (BadCredentialsException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }

	        final UserDetails userDetails = userimpl.loadUserByUsername(authRequest.getEmail());
	        final String token = jwtTokenUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new AuthResponse(token));
	    }
	
}