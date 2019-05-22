package nl.saxion.budgetblackboard.models;

public class Subject extends Header {
	private int credits;
	private int duration;

	public Subject(String name, int difficulty, int credits, int duration) {
		super(name, difficulty);
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
