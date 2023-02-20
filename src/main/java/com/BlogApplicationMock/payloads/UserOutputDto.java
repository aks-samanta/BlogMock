package com.BlogApplicationMock.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserOutputDto {
	private Integer userId;

	private String firstName;

	private String lastName;

	private String email;

	private String gender;

	private String mobileNumber;

	private int age;

}
