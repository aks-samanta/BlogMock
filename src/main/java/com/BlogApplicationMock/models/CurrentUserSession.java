package com.BlogApplicationMock.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

	@Id
	@Column(nullable = false)
	private Integer userId;

	@Column(nullable = false)
	private LocalDateTime timeStamp;

	@Column(length = 36, unique = true, nullable = false)
	private String sessionKey;

}
