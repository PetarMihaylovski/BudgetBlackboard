package nl.saxion.budgetblackboard.users;

import nl.saxion.budgetblackboard.models.Subject;

import java.util.ArrayList;

public class Teacher extends Person {
	private ArrayList<Subject> subjectsTaught;

	public Teacher(String email, String password) {
		super(email, password);
		this.subjectsTaught = new ArrayList<>();
	}

	public void addTaughtSubject(Subject subject) {
		if (!this.subjectsTaught.contains(subject)) {
			this.subjectsTaught.add(subject);
		}
	}

	public ArrayList<Subject> getSubjectsTaught() {
		return new ArrayList<>(this.subjectsTaught);
	}
}
