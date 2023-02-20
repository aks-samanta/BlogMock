package com.BlogApplicationMock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApplicationMock.models.Blog;
import com.BlogApplicationMock.models.Category;

public interface BlogRepo extends JpaRepository<Blog, Integer> {

	List<Blog> findByCategory(Category category);
}
