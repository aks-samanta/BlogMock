package com.BlogApplicationMock.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BLOGS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer blogId;

	@Enumerated(EnumType.STRING)
	private Category category;

	private String content;

	private LocalDateTime timeStamp;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();

}
