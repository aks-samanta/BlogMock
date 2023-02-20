package com.BlogApplicationMock.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.payloads.CommentInputDto;
import com.BlogApplicationMock.payloads.CommentOutputDto;
import com.BlogApplicationMock.payloads.SessionDto;
import com.BlogApplicationMock.services.CommentServices;
import com.BlogApplicationMock.services.LoginServices;

@RestController
public class CommentController {

	@Autowired
	private CommentServices commentServices;

	@Autowired
	private LoginServices loginServices;

	@PostMapping("/user/{blogId}")
	public ResponseEntity<CommentOutputDto> addComment(@Valid @RequestBody CommentInputDto commentDto,
			@RequestParam("sessionKey") String sessionKey, @PathVariable Integer blogId) throws UnauthorisedException {

		SessionDto session = this.loginServices.getSessionByKey(sessionKey);

		CommentOutputDto commentOutputDto = this.commentServices.createComment(blogId, commentDto, session.getUserId());

		return new ResponseEntity<CommentOutputDto>(commentOutputDto, HttpStatus.OK);
	}

}
