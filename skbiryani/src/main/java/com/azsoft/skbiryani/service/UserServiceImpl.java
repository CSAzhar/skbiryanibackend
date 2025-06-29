package com.azsoft.skbiryani.service;

import org.springframework.stereotype.Service;

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
	
	
	@Override
	public ResponseUserCreate registerUser(UserCreateRequest userCreateRequest) {
		
		UserEntity user =  userMapper.requestUserEntityToUserEntity(userCreateRequest);
		UserEntity savedUser = userRepository.save(user);
		ResponseUserCreate response = userMapper.userEntityToResponseUserCreate(savedUser);
		return response;
	}


	@Override
	public String findByUserIdByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
