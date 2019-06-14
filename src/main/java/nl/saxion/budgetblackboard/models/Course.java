package nl.saxion.budgetblackboard.models;

import com.fasterxml.jackson.annotation.*;

public class Course extends Header {
	@JsonProperty
	private int minCreditsToPass;
	@JsonProperty
	private int duration;
//	@JsonIgnore
//	private ArrayList<Subject> subjects;

	@JsonCreator
	public Course(String name, int difficulty, int minCreditsToPass, int duration) {
		super(name, difficulty);
		this.minCreditsToPass = minCreditsToPass;
		this.duration = duration;
//		this.subjects = new ArrayList<>();
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

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		//TODO: create a nice toString method.
		return "Course name: " + getName();
	}
}
