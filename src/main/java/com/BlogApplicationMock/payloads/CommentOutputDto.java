package com.BlogApplicationMock.payloads;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentOutputDto {
	private Integer commentId;
	private String comment;
	private LocalDateTime timeStamp;
}
