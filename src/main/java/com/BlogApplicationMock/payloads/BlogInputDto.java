package com.BlogApplicationMock.payloads;

import com.BlogApplicationMock.models.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogInputDto {

	private Category category;
	private String content;
}
