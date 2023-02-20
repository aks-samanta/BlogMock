package com.BlogApplicationMock.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApplicationMock.exceptions.CommentException;
import com.BlogApplicationMock.exceptions.UnauthorisedException;
import com.BlogApplicationMock.exceptions.UserException;
import com.BlogApplicationMock.models.Blog;
import com.BlogApplicationMock.models.Comment;
import com.BlogApplicationMock.payloads.CommentInputDto;
import com.BlogApplicationMock.payloads.CommentOutputDto;
import com.BlogApplicationMock.repositories.BlogRepo;
import com.BlogApplicationMock.repositories.CommentRepo;

@Service
public class CommentServicesImpl implements CommentServices {

	@Autowired
	private BlogRepo blogRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentOutputDto createComment(Integer blogId, CommentInputDto commentDto, Integer userId)
			throws UnauthorisedException {

		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setTimeStamp(LocalDateTime.now());

		Blog blog = this.blogRepo.findById(blogId).orElseThrow();

		if (blog.getUser().getUserId() != userId) {
			blog.getComments().add(comment);
			comment.setBlog(blog);
			Blog savedBlog = this.blogRepo.save(blog);
			Comment savedComment = savedBlog.getComments().get(savedBlog.getComments().size() - 1);

			return this.modelMapper.map(savedComment, CommentOutputDto.class);
		} else {
			throw new UnauthorisedException("You Are Not Authorised To Add Comment To This Blog.");
		}

	}

	@Override
	public CommentOutputDto deleteComment(Integer commentId, Integer userId)
			throws CommentException, UserException, UnauthorisedException {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new CommentException("Comment Not Found With Comment ID " + commentId));

		if (comment.getBlog().getUser().getUserId() == userId) {

			comment.getBlog().getComments().remove(comment);
			this.commentRepo.delete(comment);

			return this.modelMapper.map(comment, CommentOutputDto.class);
		} else {
			throw new UnauthorisedException("You Are Not Authorised To Delete This Comment");
		}
	}

}
