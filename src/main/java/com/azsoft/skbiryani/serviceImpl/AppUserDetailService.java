package com.azsoft.skbiryani.serviceImpl;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.entity.UserEntity;
import com.azsoft.skbiryani.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByMobile(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found By Mobile"));
		return new User(user.getMobile(), user.getPassword(), Collections.emptyList());
	}
	

}
