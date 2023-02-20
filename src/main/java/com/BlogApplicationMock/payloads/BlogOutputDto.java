package com.BlogApplicationMock.payloads;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.BlogApplicationMock.models.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogOutputDto {

	private Integer blogId;

	private Category category;

	private String content;

	private LocalDateTime timeStamp;

	private UserOutputDto user;

	private List<CommentOutputDto> comments = new ArrayList<>();

}
