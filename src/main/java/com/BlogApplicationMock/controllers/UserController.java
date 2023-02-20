package com.BlogApplicationMock.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApplicationMock.exceptions.BlogException;
import com.BlogApplicationMock.exceptions.CommentException;
import com.BlogApplicationMock.exceptions.LoginException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.exceptions.UserException;
import com.BlogApplicationMock.payloads.BlogOutputDto;
import com.BlogApplicationMock.payloads.CommentOutputDto;
import com.BlogApplicationMock.payloads.LoginDto;
import com.BlogApplicationMock.payloads.SessionDto;
import com.BlogApplicationMock.payloads.UserInputDto;
import com.BlogApplicationMock.payloads.UserOutputDto;
import com.BlogApplicationMock.services.BlogServices;
import com.BlogApplicationMock.services.CommentServices;
import com.BlogApplicationMock.services.LoginServices;
import com.BlogApplicationMock.services.UserServices;

@RestController
@RequestMapping("/masaiblog/user")
public class UserController {

	@Autowired
	private BlogServices blogServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private LoginServices loginServices;

	@Autowired
	private CommentServices commentServices;

	@PostMapping("/register")
	public ResponseEntity<UserOutputDto> registerUser(@Valid @RequestBody UserInputDto userInputDto) {

		UserOutputDto userOutputDto = this.userServices.registerUser(userInputDto);

		return new ResponseEntity<UserOutputDto>(userOutputDto, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<SessionDto> loginUser(LoginDto loginDto) throws LoginException, UserException {

		SessionDto sessionDto = this.loginServices.login(loginDto);

		return new ResponseEntity<SessionDto>(sessionDto, HttpStatus.OK);
	}

	@GetMapping("/blogs")
	public ResponseEntity<List<BlogOutputDto>> getAllBlogs(@RequestParam("sessionKey") String sessionKey) {

		List<BlogOutputDto> blogs = this.blogServices.getAllBlogs();

		return new ResponseEntity<List<BlogOutputDto>>(blogs, HttpStatus.OK);

	}

	@DeleteMapping("/blog/{blogId}")
	public ResponseEntity<BlogOutputDto> deleteBlog(@PathVariable Integer blogId,
			@RequestParam("sessionKey") String sessionKey) throws UnauthorisedException, BlogException, UserException {

		SessionDto session = this.loginServices.getSessionByKey(sessionKey);

		BlogOutputDto blogOutputDto = this.blogServices.deleteBlog(blogId, session.getUserId());

		return new ResponseEntity<BlogOutputDto>(blogOutputDto, HttpStatus.OK);

	}

	@DeleteMapping("/blog/comment/{commentId}")
	public ResponseEntity<CommentOutputDto> deletecomment(@RequestParam("sessionKey") String sessionKey,
			@PathVariable Integer commentId) throws CommentException, UserException, UnauthorisedException {

		SessionDto session = this.loginServices.getSessionByKey(sessionKey);

		CommentOutputDto commentOutputDto = this.commentServices.deleteComment(commentId, session.getUserId());

		return new ResponseEntity<CommentOutputDto>(commentOutputDto, HttpStatus.OK);
	}

}
