package com.BlogApplicationMock.services;

import com.BlogApplicationMock.payloads.UserInputDto;
import com.BlogApplicationMock.payloads.UserOutputDto;

public interface UserServices {

	UserOutputDto registerUser(UserInputDto userDto);
	
	
}
