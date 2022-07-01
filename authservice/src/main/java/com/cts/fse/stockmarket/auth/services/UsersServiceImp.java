package com.cts.fse.stockmarket.auth.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.auth.model.Users;
import com.cts.fse.stockmarket.auth.repository.UsersRepository;

@Service
public class UsersServiceImp implements IUserService, UserDetailsService {

	@Autowired
	UsersRepository endUserRepository;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public Integer saveEndUser(Users endUser) {
		endUser.setPassword(encoder.encode(endUser.getPassword()));
		return endUserRepository.save(endUser).getId();
	}

	@Override
	public Optional<Users> findByUserName(String userName) {
		return endUserRepository.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Users> endUser = findByUserName(userName);
		if (endUser.isEmpty()) {
			throw new UsernameNotFoundException("Users not found");
		}
		
//		return new User(userName, endUser.get().getPassword(), endUser.get().getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
		
		//Method lelev
		return new User(userName, endUser.get().getPassword(), endUser.get().getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet()));
		
		
	}
}
