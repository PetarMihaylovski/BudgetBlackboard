package nl.saxion.budgetblackboard.models;

public class Course {
	private int minCreditsToPass;
	private int duration;

	public Course(int minCreditsToPass, int duration) {
		this.minCreditsToPass = minCreditsToPass;
		this.duration = duration;
	}

	public int getMinCreditsToPass() {
		return minCreditsToPass;
	}

	public int getDuration() {
		return duration;
	}
}
