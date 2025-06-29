package com.azsoft.skbiryani.service;

import com.azsoft.skbiryani.io.security.ResponseUserCreate;
import com.azsoft.skbiryani.io.security.UserCreateRequest;

public interface IUserService {

	ResponseUserCreate registerUser(UserCreateRequest userCreateRequest);
	
	Long findByUserId();
}
