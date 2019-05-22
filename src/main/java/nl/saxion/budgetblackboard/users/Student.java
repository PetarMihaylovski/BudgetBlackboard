package nl.saxion.budgetblackboard.users;

import nl.saxion.budgetblackboard.models.Subject;

import java.util.ArrayList;

public class Student extends Person {
	private ArrayList<Subject> enrolledSubjects;

	public Student(String email, String password) {
		super(email, password);
		this.enrolledSubjects = new ArrayList<>();
	}

	public void addEnrolledSubject(Subject subject) {
		if (!this.enrolledSubjects.contains(subject)) {
			this.enrolledSubjects.add(subject);
		}
	}

	public ArrayList<Subject> getEnrolledSubjects() {
		return new ArrayList<>(this.enrolledSubjects);
	}
}
