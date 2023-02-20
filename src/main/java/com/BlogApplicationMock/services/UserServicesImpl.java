package com.BlogApplicationMock.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApplicationMock.models.User;
import com.BlogApplicationMock.payloads.UserInputDto;
import com.BlogApplicationMock.payloads.UserOutputDto;
import com.BlogApplicationMock.repositories.UserRepo;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserOutputDto registerUser(UserInputDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		User savedUser = this.userRepo.save(user);

		return this.modelMapper.map(savedUser, UserOutputDto.class);
	}

}
