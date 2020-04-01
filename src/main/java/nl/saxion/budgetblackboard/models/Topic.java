package nl.saxion.budgetblackboard.models;

public class Topic extends Header {
	private int week;
	private static int lastAssignedID = 0;
	private int ID;

	public Topic(String name, int difficulty, int week) {
		super(name, difficulty);
		this.week = week;
		this.ID = lastAssignedID;
		++lastAssignedID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getWeek() {
		return week;
	}

	public int getID() {
		return ID;
	}
}