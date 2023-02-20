package com.BlogApplicationMock.services;

import com.BlogApplicationMock.exceptions.CommentException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.exceptions.UserException;
import com.BlogApplicationMock.payloads.CommentInputDto;
import com.BlogApplicationMock.payloads.CommentOutputDto;

public interface CommentServices {

	public CommentOutputDto createComment(Integer blogId, CommentInputDto commentDto, Integer userId)
			throws UnauthorisedException;

	public CommentOutputDto deleteComment(Integer commentId, Integer userId)
			throws CommentException, UserException, UnauthorisedException;
}
