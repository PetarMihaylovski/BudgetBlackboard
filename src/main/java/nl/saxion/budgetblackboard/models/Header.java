package nl.saxion.budgetblackboard.models;

public abstract class Header {
	private String name;
	private int difficulty;

	public Header(String name, int difficulty) {
		this.name = name;
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public int getDifficulty() {
		return difficulty;
	}
}
