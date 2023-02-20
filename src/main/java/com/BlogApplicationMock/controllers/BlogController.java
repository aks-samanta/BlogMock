package com.BlogApplicationMock.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApplicationMock.exceptions.BlogException;
import com.BlogApplicationMock.exceptions.LoginException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.models.Category;
import com.BlogApplicationMock.payloads.BlogInputDto;
import com.BlogApplicationMock.payloads.BlogOutputDto;
import com.BlogApplicationMock.payloads.SessionDto;
import com.BlogApplicationMock.services.BlogServices;
import com.BlogApplicationMock.services.LoginServices;

@RestController
@RequestMapping("/masaiblog/")
public class BlogController {

	@Autowired
	private BlogServices blogServices;

	@Autowired
	private LoginServices loginServices;

	@PostMapping("user/blog")
	public ResponseEntity<BlogOutputDto> createBlog(@Valid @RequestBody BlogInputDto blogInputDto,
			@RequestParam("sessionKey") String sessionKey) throws LoginException, BlogException, UnauthorisedException {

		SessionDto session = loginServices.getSessionByKey(sessionKey);

		BlogOutputDto blogOutputDto = blogServices.createBlog(blogInputDto, session.getUserId());

		return new ResponseEntity<BlogOutputDto>(blogOutputDto, HttpStatus.CREATED);
	}

	@GetMapping("user/blog/{category}")
	public ResponseEntity<List<BlogOutputDto>> getBlogsByCategory(@Valid @PathVariable Category category)
			throws BlogException {

		List<BlogOutputDto> blogs = this.blogServices.getBlogsByCategory(category);

		return new ResponseEntity<List<BlogOutputDto>>(blogs, HttpStatus.OK);
	}

}
