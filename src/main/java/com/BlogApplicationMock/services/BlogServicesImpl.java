package com.BlogApplicationMock.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApplicationMock.exceptions.BlogException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.exceptions.UserException;
import com.BlogApplicationMock.models.Blog;
import com.BlogApplicationMock.models.Category;
import com.BlogApplicationMock.models.User;
import com.BlogApplicationMock.payloads.BlogInputDto;
import com.BlogApplicationMock.payloads.BlogOutputDto;
import com.BlogApplicationMock.repositories.BlogRepo;
import com.BlogApplicationMock.repositories.UserRepo;

@Service
public class BlogServicesImpl implements BlogServices {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BlogRepo blogRepo;

	@Override
	public BlogOutputDto createBlog(BlogInputDto blogDto, Integer userId) {
		Blog blog = this.modelMapper.map(blogDto, Blog.class);

		User user = this.userRepo.findById(userId).orElseThrow();
		/*
		 * not throwing specific exception because user is already logged in. and we are
		 * getting the user id from session table so no chance of wrong user id.
		 */

		blog.setTimeStamp(LocalDateTime.now());
		blog.setUser(user);

		Blog savedBlog = this.blogRepo.save(blog);

		return this.modelMapper.map(savedBlog, BlogOutputDto.class);

	}

	@Override
	public List<BlogOutputDto> getAllBlogs() {
		List<Blog> blogs = this.blogRepo.findAll();

		List<BlogOutputDto> blogDtos = blogs.stream().map((blog) -> this.modelMapper.map(blog, BlogOutputDto.class))
				.collect(Collectors.toList());

		return blogDtos;
	}

	@Override
	public List<BlogOutputDto> getBlogsByCategory(Category category) throws BlogException {
		List<Blog> blogs = this.blogRepo.findByCategory(category);

		if (blogs.size() == 0) {
			throw new BlogException("Blog Not Found With Category " + category);
		}

		List<BlogOutputDto> blogDtos = blogs.stream().map((blog) -> this.modelMapper.map(blog, BlogOutputDto.class))
				.collect(Collectors.toList());

		return blogDtos;
	}

	@Override
	public BlogOutputDto deleteBlog(Integer blogId, Integer userId)
			throws BlogException, UserException, UnauthorisedException {

		Blog blog = this.blogRepo.findById(blogId)
				.orElseThrow(() -> new BlogException("Blog Not Found With BLog ID " + blogId));

		if (blog.getUser().getUserId() == userId) {
			this.blogRepo.delete(blog);
		} else {
			throw new UnauthorisedException("You Are Not Authorised To Delete This Blog.");
		}

		return this.modelMapper.map(blog, BlogOutputDto.class);
	}

}
