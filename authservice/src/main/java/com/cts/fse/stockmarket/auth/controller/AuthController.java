package com.cts.fse.stockmarket.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.stockmarket.auth.model.MessageResponse;
import com.cts.fse.stockmarket.auth.model.Role;
import com.cts.fse.stockmarket.auth.model.User;
import com.cts.fse.stockmarket.auth.repository.RoleRepository;
import com.cts.fse.stockmarket.auth.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/springjwt")
public class AuthController {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User signUpRequest) {
		if (userRepository.existsByName(signUpRequest.getName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		List<Role> receivedRoles = signUpRequest.getRoles();
		if (receivedRoles == null || receivedRoles.isEmpty()) {
			receivedRoles = new ArrayList<>();
			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			receivedRoles.add(userRole);
			signUpRequest.setRoles(receivedRoles);
			if (!roleRepository.existsByName("ROLE_USER"))
				roleRepository.saveAll(receivedRoles);
		} else {

			receivedRoles.forEach((role) -> {
				if (!roleRepository.existsByName(role.getName()))
					roleRepository.save(role);
			});

		}
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		userRepository.save(signUpRequest);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
