package com.BlogApplicationMock.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApplicationMock.exceptions.LoginException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.exceptions.UserException;
import com.BlogApplicationMock.models.CurrentUserSession;
import com.BlogApplicationMock.models.User;
import com.BlogApplicationMock.payloads.LoginDto;
import com.BlogApplicationMock.payloads.SessionDto;
import com.BlogApplicationMock.repositories.SessionRepo;
import com.BlogApplicationMock.repositories.UserRepo;

@Service
public class LoginServicesImpl implements LoginServices {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private SessionRepo sessionRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override

	public SessionDto login(LoginDto loginDto) throws LoginException, UserException {

		User user = this.userRepo.findByEmail(loginDto.getEmail());
		if (user == null) {
			throw new UserException("No User Found With Email ID " + loginDto.getEmail());
		}

		if (!loginDto.getPassword().equals(user.getPassword())) {
			throw new LoginException("Incorrect Password !!");
		}

		CurrentUserSession activeSession = this.sessionRepo.findByUserId(user.getUserId());

		if (activeSession == null) {

			CurrentUserSession session = new CurrentUserSession();
			session.setUserId(user.getUserId());
			session.setTimeStamp(LocalDateTime.now());
			session.setSessionKey(UUID.randomUUID().toString());
			CurrentUserSession savedSession = this.sessionRepo.save(session);

			return this.modelMapper.map(savedSession, SessionDto.class);
		} else {
			throw new LoginException("User Already Logged in With Email Id : " + user.getEmail());
		}
	}

	@Override

	public String logout(Integer userId) throws LoginException {

		CurrentUserSession userSession = this.sessionRepo.findById(userId).orElseThrow();

		this.sessionRepo.delete(userSession);

		return "Logged Out Successfully !!";

	}

	@Override
	public SessionDto getSessionByKey(String key) throws UnauthorisedException {
		CurrentUserSession sess = this.sessionRepo.findBySessionKey(key);

		if (sess == null) {
			throw new UnauthorisedException("Invalid Session Key!! ");
		}

		return this.modelMapper.map(sess, SessionDto.class);
	}
}
