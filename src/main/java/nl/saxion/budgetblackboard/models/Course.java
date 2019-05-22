package nl.saxion.budgetblackboard.models;

import java.util.ArrayList;

public class Course extends Header {
	private int minCreditsToPass;
	private int duration;
	private ArrayList<Subject> subjects;

	public Course(String name, int difficulty, int minCreditsToPass, int duration) {
		super(name, difficulty);
		this.minCreditsToPass = minCreditsToPass;
		this.duration = duration;
		this.subjects = new ArrayList<>();
	}

	public int getMinCreditsToPass() {
		return minCreditsToPass;
	}

	public int getDuration() {
		return duration;
	}

	public void addSubject(Subject subject) {
		if (!this.subjects.contains(subject)) {
			this.subjects.add(subject);
		}
	}

	public ArrayList<Subject> getSubjects() {
		return new ArrayList<>(this.subjects);
	}
}
