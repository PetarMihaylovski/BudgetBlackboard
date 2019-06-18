package nl.saxion.budgetblackboard.models;

public class Subject extends Header {
	private int credits;
	private int duration;
	private static int lastAssignedID = 0;
	private int ID;

	public Subject(String name, int difficulty, int credits, int duration) {
		super(name, difficulty);
		this.credits = credits;
		this.duration = duration;
		this.ID = lastAssignedID;
		++lastAssignedID;
	}

	public int getID() {
		return ID;
	}

	public int getCredits() {
		return credits;
	}

	public int getDuration() {
		return duration;
	}
}
