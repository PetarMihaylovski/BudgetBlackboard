package nl.saxion.budgetblackboard.models;


import java.util.ArrayList;

public class Course extends Header {
	private int minCreditsToPass;
	private int duration;
	private ArrayList<Subject> subjects;
	private static int lastAssignedID = 0;
	private int ID;

	public Course(String name, int difficulty, int minCreditsToPass, int duration) {
		super(name, difficulty);
		this.minCreditsToPass = minCreditsToPass;
		this.duration = duration;
		this.subjects = new ArrayList<>();
		this.ID = lastAssignedID;
		++lastAssignedID;
	}

	public int getMinCreditsToPass() {
		return minCreditsToPass;
	}

	public void setMinCreditsToPass(int minCreditsToPass) {
		this.minCreditsToPass = minCreditsToPass;
	}

	public int getDuration() {
		return duration;
	}

	public int getID() {
		return ID;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<Subject> getSubjects() {
		return new ArrayList<>(this.subjects);
	}

	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Override
	public String toString() {
		//TODO: create a nice toString method.
		return "Course name: " + getName();
	}
}
