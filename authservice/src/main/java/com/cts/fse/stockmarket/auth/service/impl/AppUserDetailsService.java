package com.cts.fse.stockmarket.auth.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cts.fse.stockmarket.auth.model.User;
import com.cts.fse.stockmarket.auth.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class AppUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> userOptional = userRepository.findByName(userName);
		if (!userOptional.isPresent()) {
			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", userName));
		}
		User user = userOptional.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
				user.getPassword(), authorities);

		return userDetails;
	}
}