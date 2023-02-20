package com.BlogApplicationMock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApplicationMock.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
