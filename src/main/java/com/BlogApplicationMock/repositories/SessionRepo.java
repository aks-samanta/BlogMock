package com.BlogApplicationMock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApplicationMock.models.CurrentUserSession;

public interface SessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findBySessionKey(String sessionKey);

	public CurrentUserSession findByUserId(Integer userId);

}
