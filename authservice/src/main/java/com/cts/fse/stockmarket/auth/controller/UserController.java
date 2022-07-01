package com.cts.fse.stockmarket.auth.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.stockmarket.auth.config.JwtConfig;
import com.cts.fse.stockmarket.auth.dto.UsersRequest;
import com.cts.fse.stockmarket.auth.dto.UsersResponse;
import com.cts.fse.stockmarket.auth.model.Users;
import com.cts.fse.stockmarket.auth.services.UsersServiceImp;

@RestController
@RequestMapping("/end-user")
public class UserController {

	@Autowired
	UsersServiceImp endUserServiceImp;

	@Autowired
	JwtConfig jwtConfig;

	@Autowired
	AuthenticationManager authManager;

	@PostMapping("/save")
	public ResponseEntity<String> saveEndUser(@RequestBody Users endUser) {
		Integer saveEndUser = endUserServiceImp.saveEndUser(endUser);
		return new ResponseEntity<String>(new String("End Users Successfully created with id : " + saveEndUser),
				HttpStatus.OK);
	}

	@PostMapping("/token")
	public ResponseEntity<UsersResponse> getToken(@RequestBody UsersRequest endUserRequest) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(endUserRequest.getUserName(), endUserRequest.getPassword()));
		System.err.println(authentication.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<UsersResponse>(
				new UsersResponse(jwtConfig.generateToken(authentication),
						"Token generated successfully"),
				HttpStatus.OK);
	}

	@GetMapping("/data")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RolesAllowed("ROLE_ENDUSER")
	@Secured("ROLE_RAM")
	public ResponseEntity<Principal> getAccessData(Principal principal) {
		return ResponseEntity.ok( principal);
	}
	
	@GetMapping("/test")
//	@PreAuthorize("hasRole('ROLE_ENDUSER')")
//	@RolesAllowed("ROLE_ENDUSER")
//	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> getAccessDataTest() {
		System.err.println("Thred : " + Thread.currentThread() + " with this time "+new Date());
		return ResponseEntity.ok("Thred : " + Thread.currentThread() + " with this time "+new Date());
	}
}
