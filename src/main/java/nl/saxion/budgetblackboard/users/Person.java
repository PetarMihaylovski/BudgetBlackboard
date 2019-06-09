package nl.saxion.budgetblackboard.users;

import javax.validation.constraints.Email;

public abstract class Person {

	@Email
	private String email;

	private String password;

	public Person(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
