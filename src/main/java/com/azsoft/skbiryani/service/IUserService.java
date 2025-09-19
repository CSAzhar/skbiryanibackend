package com.azsoft.skbiryani.service;

import com.azsoft.skbiryani.dto.ResponseUserCreate;
import com.azsoft.skbiryani.dto.UserCreateRequest;

public interface IUserService {

	ResponseUserCreate registerUser(UserCreateRequest userCreateRequest);
	
	Long findByUserId();
}
