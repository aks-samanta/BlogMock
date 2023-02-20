package com.BlogApplicationMock.services;

import java.util.List;

import com.BlogApplicationMock.exceptions.BlogException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.exceptions.UserException;
import com.BlogApplicationMock.models.Category;
import com.BlogApplicationMock.payloads.BlogInputDto;
import com.BlogApplicationMock.payloads.BlogOutputDto;

public interface BlogServices {

	public BlogOutputDto createBlog(BlogInputDto blogDto, Integer userId);

	public List<BlogOutputDto> getAllBlogs();

	public List<BlogOutputDto> getBlogsByCategory(Category category) throws BlogException;

	public BlogOutputDto deleteBlog(Integer blogId, Integer userId)
			throws BlogException, UserException, UnauthorisedException;

}
