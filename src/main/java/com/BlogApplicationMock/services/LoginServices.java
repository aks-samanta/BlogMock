package com.BlogApplicationMock.services;

import com.BlogApplicationMock.exceptions.LoginException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.exceptions.UserException;
import com.BlogApplicationMock.payloads.LoginDto;
import com.BlogApplicationMock.payloads.SessionDto;

public interface LoginServices {

	public SessionDto login(LoginDto loginDto) throws LoginException, UserException;

	public String logout(Integer userId) throws LoginException;

	public SessionDto getSessionByKey(String key) throws UnauthorisedException;
}
