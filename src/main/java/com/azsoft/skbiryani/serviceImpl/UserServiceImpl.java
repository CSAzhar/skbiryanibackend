package com.azsoft.skbiryani.serviceImpl;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.dto.ResponseUserCreate;
import com.azsoft.skbiryani.dto.UserCreateRequest;
import com.azsoft.skbiryani.entity.UserEntity;
import com.azsoft.skbiryani.mapper.UserEntityMapper;
import com.azsoft.skbiryani.repository.UserRepository;
import com.azsoft.skbiryani.service.IAuthenticationFacade;
import com.azsoft.skbiryani.service.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{
	
	private final UserRepository userRepository;
	private final UserEntityMapper userMapper;
	private final IAuthenticationFacade authenticationFacade;
	
	
	@Override
	public ResponseUserCreate registerUser(UserCreateRequest userCreateRequest) {
		userCreateRequest.setMobile("+91"+userCreateRequest.getMobile());
		UserEntity user =  userMapper.requestUserEntityToUserEntity(userCreateRequest);
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
