package com.BlogApplicationMock.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInputDto {

	@NotNull
	@Size(min = 2, max = 30)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 30)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String lastName;

	@Email
	private String email;

	private String gender;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "^[0-9]+$")
	private String mobileNumber;

	@Min(12)
	private int age;

	@NotNull
	@Size(min = 6, max = 12)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,12}$")
	private String password;

}
