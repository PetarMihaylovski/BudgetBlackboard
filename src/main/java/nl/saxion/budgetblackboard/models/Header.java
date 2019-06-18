package nl.saxion.budgetblackboard.models;

public abstract class Header {
	private String name;
	private int difficulty;

	Header(String name, int difficulty) {
		this.name = name;
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
