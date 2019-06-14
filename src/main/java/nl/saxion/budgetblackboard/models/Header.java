package nl.saxion.budgetblackboard.models;

public abstract class Header {
	private String name;
	private int difficulty;
	private static int lastAssignedID = 0;
	private int ID;

	Header(String name, int difficulty) {
		this.name = name;
		this.difficulty = difficulty;
		++lastAssignedID;
		this.ID = lastAssignedID;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
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
