package nl.saxion.budgetblackboard.models;

public class Topic extends Header {
	private int week;

	public Topic(String name, int difficulty, int week) {
		super(name, difficulty);
		this.week = week;
	}

	public int getWeek() {
		return week;
	}
}