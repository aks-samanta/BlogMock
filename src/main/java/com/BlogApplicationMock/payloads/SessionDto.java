package com.BlogApplicationMock.payloads;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionDto {

	private Integer userId;

	private LocalDateTime timeStamp;

	private String sessionKey;
	
}
