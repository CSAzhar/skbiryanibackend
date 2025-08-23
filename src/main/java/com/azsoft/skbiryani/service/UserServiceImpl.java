package com.azsoft.skbiryani.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.entity.Role;
import com.azsoft.skbiryani.entity.UserEntity;
import com.azsoft.skbiryani.io.security.ResponseUserCreate;
import com.azsoft.skbiryani.io.security.UserCreateRequest;
import com.azsoft.skbiryani.mapper.UserEntityMapper;
import com.azsoft.skbiryani.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{
	
	private final UserRepository userRepository;
	private final UserEntityMapper userMapper;
	private final IAuthenticationFacade authenticationFacade;
	
	
	@Override
	public ResponseUserCreate registerUser(UserCreateRequest userCreateRequest) {
		
		UserEntity user =  userMapper.requestUserEntityToUserEntity(userCreateRequest);
		user.setRole(Role.USER);
		UserEntity savedUser = userRepository.save(user);
		ResponseUserCreate response = userMapper.userEntityToResponseUserCreate(savedUser);
		return response;
	}


	@Override
	public Long findByUserId() {
		String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
		return userRepository.findByEmail(loggedInUserEmail)
							.orElseThrow(() -> new UsernameNotFoundException("username not dound"))
							.getUserId();
		
	}


	

}
