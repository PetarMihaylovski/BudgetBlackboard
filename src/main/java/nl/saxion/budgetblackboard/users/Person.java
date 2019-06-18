package nl.saxion.budgetblackboard.users;

public abstract class Person {
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
