package nl.saxion.budgetblackboard.models;

import java.util.ArrayList;

public class Subject extends Header {
	private int credits;
	private int duration;
	private static int lastAssignedID = 0;
	private int ID;
	private ArrayList<Topic> topics;

	public Subject(String name, int difficulty, int credits, int duration) {
		super(name, difficulty);
		this.credits = credits;
		this.duration = duration;
		this.topics = new ArrayList<>();
		this.ID = lastAssignedID;
		++lastAssignedID;
	}

	public ArrayList<Topic> getTopics() {
		return new ArrayList<>(this.topics);
	}

	public void setTopics(ArrayList<Topic> topics) {
		this.topics = topics;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void addTopic(Topic topic){
		this.topics.add(topic);
	}

	public void setID(int ID) {
		this.ID = ID;
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
