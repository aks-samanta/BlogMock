package com.BlogApplicationMock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApplicationMock.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
