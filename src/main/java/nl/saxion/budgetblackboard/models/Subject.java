package nl.saxion.budgetblackboard.models;

public class Subject {
	private int credits;
	private int duration;

	public Subject(int credits, int duration) {
		this.credits = credits;
		this.duration = duration;
	}

	public int getCredits() {
		return credits;
	}

	public int getDuration() {
		return duration;
	}
}
